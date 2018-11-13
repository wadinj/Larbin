package io.larbin.task.seekingAlpha.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SeekingAlphaSearch {

    @JsonProperty("symbols")
    var symbols: List<SeekingAlphaSymbol>? = null

    @JsonProperty("query")
    var query: List<String>? = null
}