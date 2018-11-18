package io.larbin.task.seekingAlpha

import io.larbin.task.seekingAlpha.entities.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.io.File.separator
import java.util.regex.Pattern

@Component
class SeekingAlphaSpider(@Autowired private val repository: EarningCallRepository) {

    companion object {
        var state: SpiderState = SpiderState()
    }

    //@Scheduled(fixedDelay = 600000)
    fun updateTranscripts() {
        var seekingAlphaBaseUri = "https://seekingalpha.com"
        var searchApi = "https://seekingalpha.com/api/common/ac/search?term={0}&symbols=1&pages=10000"
        var transcriptListUrl = "https://seekingalpha.com/symbol/{0}/earnings/transcripts"
        var httpClient = RestTemplate()
        var startLetter = if(state.letter == null) 65 else state.letter
        if(startLetter != null) {
            for (asciiChar in startLetter until 90 step 1) {
                var symbols: MutableList<SeekingAlphaSymbol>? = null
                var searchTarget = searchApi.replace("{0}", asciiChar.toChar().toString())
                try {
                    var searchResult = httpClient.getForEntity<SeekingAlphaSearch>(searchTarget, SeekingAlphaSearch::class.java)
                    symbols = searchResult.body.symbols
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    state = SpiderState(null, asciiChar, null)
                    return
                }
                var symbolIndex = if (state.symbol == null) 0 else state.symbol
                if (symbols != null && symbolIndex != null) {
                    for (i in symbolIndex..symbols.size) {
                        if (symbols[i] != null) {
                            var earningCall = EarningCall()
                            earningCall.reference.companyName = symbols[i].companyName
                            earningCall.reference.ticker = symbols[i].ticker
                            var transcripts: Document;
                                var transcriptListUrl = transcriptListUrl.replace("{0}", symbols[i].ticker, false)
                            try {
                                transcripts = Jsoup.connect(transcriptListUrl).get()
                                Thread.sleep(1000)
                            } catch(e: Exception) {
                                state = SpiderState(i, asciiChar, null)
                                return
                            }
                            var links = transcripts.select("#headlines_transcripts > div > ul > li > div.content > div > a").eachAttr("href")
                                    .filter { c -> c.contains("earnings-call-transcript") }
                            var linkIndex = if(state.linkIndex == null) 0 else state.linkIndex
                            if(linkIndex != null) {
                                for (l in linkIndex..links.size) {
                                    var quarterRegex = Regex("q\\d{1}")
                                    var yearegex = Regex("-\\d{4}-")
                                    earningCall.reference.quarter = quarterRegex.find(links[l])?.groupValues?.singleOrNull()
                                    earningCall.reference.year = yearegex.find(links[l])?.groupValues?.singleOrNull()?.replace("-", "")
                                    var transcriptUrl = seekingAlphaBaseUri + links[l]
                                    try {
                                        var document = Jsoup.connect(transcriptUrl).get()
                                        var article = document.select("#a-body").first()
                                        earningCall.reference.title = document.select("#a-hd > h1").text()
                                        parseArticle(article, earningCall)
                                        repository.insert(earningCall)
                                        state = SpiderState(null, null, null)
                                    } catch (e: Exception) {
                                        state = SpiderState(i, l, asciiChar)
                                        return
                                    }
                                    Thread.sleep(2000)
                                }
                            }
                        }
                    }
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
                        if(!currentSpeech.isNullOrEmpty()) {
                            earningCall.speeches.add(Speech(speechOrder++, currentSpeaker, currentSpeech))
                            currentSpeech = ""
                        }
                        currentSpeaker = childElement.text()
                    }
                }
            }
        }
        // add the last speech
        if(!currentSpeech.isNullOrEmpty())
        earningCall.speeches.add(Speech(speechOrder++, currentSpeaker, currentSpeech))
    }

    private fun getSpeakerFromText(text: String?): Speaker {
        var speaker = Speaker()
        var names = text?.split(" ")
        var affiliation = text?.split("-")
        speaker.firstName = names?.get(0)?.trim()
        speaker.lastName = names?.get(1)?.trim()
        speaker.affiliation = affiliation?.drop(1)?.joinToString("-")?.trim()
        return speaker
    }
}

data class SpiderState(var symbol: Int? = null, var letter: Int? = null, var linkIndex: Int? = null) {

}