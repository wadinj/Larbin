package io.larbin.task.seekingAlpha.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SeekingAlphaSearch {

    @JsonProperty("symbols")
    var symbols: MutableList<SeekingAlphaSymbol>? = null

    @JsonProperty("query")
    var query: MutableList<String>? = null
}