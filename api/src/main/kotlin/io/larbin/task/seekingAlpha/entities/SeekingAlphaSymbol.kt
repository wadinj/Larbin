package io.larbin.task.seekingAlpha.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SeekingAlphaSymbol {

    @JsonProperty("name")
    var ticker: String = ""

    @JsonProperty("content")
    var companyName: String = ""
}