package io.larbin.task.seekingAlpha

import io.larbin.task.seekingAlpha.entities.SeekingAlphaSearch
import org.jsoup.Jsoup
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SeekingAlphaSpider {

    @Scheduled(fixedDelay = 600000)
    fun updateTranscripts() {
        var seekingAlphaBaseUri = "https://seekingalpha.com"
        var searchApi = "https://seekingalpha.com/api/common/ac/search?term={0}&symbols=1&pages=10000"
        var transcriptListUrl = "https://seekingalpha.com/symbol/{0}/earnings/transcripts"
        var httpClient = RestTemplate()
        for (asciiChar in 65..90) {
            var searchTarget = searchApi.replace("{0}", asciiChar.toChar().toString())
            var searchResult = httpClient.getForEntity<SeekingAlphaSearch>(searchTarget, SeekingAlphaSearch::class.java)
            var symbolNames = searchResult.body.symbols?.map { it.name }
            if (symbolNames != null) {
                for (symbolName in symbolNames) {
                    if (symbolName != null) {
                        var transcripts = Jsoup.connect(transcriptListUrl.replace("{0}", symbolName, false)).get()
                        var links = transcripts.select("#headlines_transcripts > div > ul > li > div.content > div > a").eachAttr("href")
                        for (link in links) {
                            var transcriptUrl = seekingAlphaBaseUri + link
                            var paragraph = Jsoup.connect(transcriptUrl).get().select("p")
                            var paraText = paragraph.text()
                        }
                    }
                }
            }
        }
    }
}