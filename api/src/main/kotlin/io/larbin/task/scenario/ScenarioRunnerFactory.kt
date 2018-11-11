package io.larbin.task.scenario

import io.larbin.api.scenario.entities.Scenario
import org.springframework.stereotype.Component

@Component
class ScenarioRunnerFactory {
        fun createScenarioRunner(scenario: Scenario) : ScenarioRunner {
            return ScenarioRunner(scenario)
        }
}