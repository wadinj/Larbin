package io.larbin.api.scenario

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/scenario")
class ScenarioController(val scenarioService: ScenarioService) {

    @GetMapping("/{id}")
    fun getScenario(@PathVariable id: Long): Scenario = scenarioService.getScenario(id)

    @PostMapping("/")
    fun createScenario(@Valid @RequestBody scenario: Scenario) =
            scenarioService.createScenario(scenario)

    @DeleteMapping("/{id}")
    fun createScenario(@PathVariable id: Long) =
            scenarioService.deleteScenario(id)
}