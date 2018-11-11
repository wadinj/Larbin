package io.larbin.api.scenario.entities

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "scenarios")
data class Scenario(val name: String,
                    val request: ScenarioRequest?,
                    val mappings: List<ScenarioMapping>,
                    val config: ScenarioConfig? = ScenarioConfig(false, 500, ""))