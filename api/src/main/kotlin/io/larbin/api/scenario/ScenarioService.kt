package io.larbin.api.scenario

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ScenarioService @Autowired constructor(@Autowired
                                             private val scenarioRepository: ScenarioRepository) {

    fun createScenario(scenario: Scenario): Scenario = scenarioRepository.insert(scenario)

    fun getScenario(id: Long): Scenario = scenarioRepository.findOne(id)

    fun deleteScenario(id: Long) = scenarioRepository.delete(id)

}