package io.larbin.api.scenario.entities


data class ScenarioRequestHeader(internal val name: String, val value: String)

data class ScenarioRequest(val url: String, val headers: List<ScenarioRequestHeader>? = null, val method: String = "GET")