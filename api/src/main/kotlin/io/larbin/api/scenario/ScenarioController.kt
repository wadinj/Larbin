package io.larbin.api.scenario

import io.larbin.api.scenario.ScenarioService
import io.larbin.api.scenario.entities.Scenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/scenario")
class ScenarioController(@Autowired private val scenarioService: ScenarioService) {

    @GetMapping()
    fun getAll(): List<Scenario> = scenarioService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Scenario = scenarioService.get(id)

    @PostMapping()
    fun create(@Valid @RequestBody scenario: Scenario) =
            scenarioService.create(scenario)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
            scenarioService.delete(id)

    @DeleteMapping()
    fun deleteAll() =
            scenarioService.deleteAll()
}