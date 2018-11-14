package io.larbin.task.seekingAlpha

import io.larbin.task.seekingAlpha.entities.EarningCall
import io.larbin.task.seekingAlpha.entities.SeekingAlphaSearch
import io.larbin.task.seekingAlpha.entities.Speaker
import io.larbin.task.seekingAlpha.entities.Speech
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SeekingAlphaSpider(@Autowired private val repository: EarningCallRepository) {

    //@Scheduled(fixedDelay = 600000)
    fun updateTranscripts() {
        var seekingAlphaBaseUri = "https://seekingalpha.com"
        var searchApi = "https://seekingalpha.com/api/common/ac/search?term={0}&symbols=1&pages=10000"
        var transcriptListUrl = "https://seekingalpha.com/symbol/{0}/earnings/transcripts"
        var httpClient = RestTemplate()
        for (asciiChar in 65..90) {
            var searchTarget = searchApi.replace("{0}", asciiChar.toChar().toString())
            var searchResult = httpClient.getForEntity<SeekingAlphaSearch>(searchTarget, SeekingAlphaSearch::class.java)
            var symbols = searchResult.body.symbols
            if (symbols != null) {
                for (symbol in symbols) {
                    if (symbol != null) {
                        var earningCall = EarningCall()
                        earningCall.reference.companyName = symbol.companyName
                        earningCall.reference.ticker = symbol.ticker
                        var transcripts = Jsoup.connect(transcriptListUrl.replace("{0}", symbol.ticker, false)).get()
                        var links = transcripts.select("#headlines_transcripts > div > ul > li > div.content > div > a").eachAttr("href")
                                .filter { c -> c.contains("earnings-call-transcript") }
                        for (link in links) {
                            var transcriptUrl = seekingAlphaBaseUri + link
                            var article = Jsoup.connect(transcriptUrl).get().select("#a-body").first()
                            parseArticle(article, earningCall)
                            repository.insert(earningCall)
                        }
                    }
                    Thread.sleep(1000)
                }
            }
        }
    }
    fun parseArticle(article: Element, earningCall: EarningCall) {
        var isExecutiveSection = false
        var isAnalystSection = false
        var speechOrder = 1
        var currentSpeaker: String = ""
        var currentSpeech: String = ""
        var childElements = article.allElements
        for(childElement in childElements) {
            if(childElement.tagName() == "p" && !childElement.children().any { c -> c.tagName() == "strong" }) {
                if (isExecutiveSection) {
                    earningCall.reference.speakers.executives?.add(getSpeakerFromText(childElement.text()))
                } else if (isAnalystSection) {
                    earningCall.reference.speakers.analysts?.add(getSpeakerFromText(childElement.text()))
                } else if (!currentSpeaker.isNullOrEmpty()) {
                    currentSpeech += childElement.text()
                }
            }
            if(childElement.tagName() == "strong") {
                when(childElement.text().toLowerCase()) {
                    "executives" -> {
                        isExecutiveSection = true
                        isAnalystSection = false
                    }
                    "analysts" -> {
                        isExecutiveSection = false
                        isAnalystSection = true
                    }
                    else -> {
                        isExecutiveSection = false
                        isAnalystSection = false
                        currentSpeaker = childElement.text()
                        if(!currentSpeech.isNullOrEmpty()) {
                            earningCall.speeches.add(Speech(speechOrder++, currentSpeaker, currentSpeech))
                            currentSpeech = ""
                        }
                    }
                }
            }
        }
    }

    private fun getSpeakerFromText(text: String?): Speaker {
        var speaker = Speaker()
        var names = text?.split(" ")
        var affiliation = text?.split("-")
        speaker.firstName = names?.get(0)?.trim()
        speaker.lastName = names?.get(1)?.trim()
        speaker.affiliation = affiliation?.drop(1)?.joinToString { i -> i }
        return speaker
    }
}