package io.larbin.api.scenario

import io.larbin.api.scenario.entities.Scenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ScenarioService @Autowired constructor(@Autowired
                                             private val scenarioRepository: ScenarioRepository) {

    fun create(scenario: Scenario): Scenario = scenarioRepository.insert(scenario)

    fun getAll(): List<Scenario> = scenarioRepository.findAll()

    fun get(id: Long): Scenario = scenarioRepository.findOne(id)

    fun deleteAll() = scenarioRepository.deleteAll()

    fun delete(id: Long) = scenarioRepository.delete(id)

}