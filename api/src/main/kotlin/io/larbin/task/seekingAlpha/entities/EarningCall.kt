package io.larbin.task.seekingAlpha.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class EarningCall {
    @JsonProperty("Referance")
    var reference: Reference = Reference()

    @JsonProperty("speeches")
    var speeches: MutableList<Speech> = mutableListOf()
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Reference {

    @JsonProperty("companyName")
    var companyName: String? = null

    @JsonProperty("title")
    var title: String? = null

    @JsonProperty("ticker")
    var ticker: String? = null

    @JsonProperty("quarter")
    var quarter: String? = null

    @JsonProperty("year")
    var year: String? = null

    @JsonProperty("datetime")
    var datetime: String? = null

    @JsonProperty("speakers")
    var speakers: Speakers = Speakers()
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Speakers {

    @JsonProperty("executives")
    var executives: MutableList<Speaker>? = mutableListOf()

    @JsonProperty("analysts")
    var analysts: MutableList<Speaker>? = mutableListOf()

    @JsonProperty("operators")
    var operators: MutableList<Speaker>? = mutableListOf()

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Speaker {

    @JsonProperty("firstName")
    var firstName: String? = null

    @JsonProperty("lastName")
    var lastName: String? = null

    @JsonProperty("title")
    var title: String? = null

    @JsonProperty("affiliation")
    var affiliation: String? = null

    @JsonProperty("datetime")
    var datetime: String? = null
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Speech {

    constructor(order: Int?, speakerName: String?, text: String?) {
        this.order = order
        this.speakerName = speakerName
        this.text = text
    }
    @JsonProperty("order")
    var order: Int? = null

    @JsonProperty("sectionId")
    var sectionId: Int? = null

    @JsonProperty("speakerName")
    var speakerName: String? = null

    @JsonProperty("text")
    var text: String? = null
}