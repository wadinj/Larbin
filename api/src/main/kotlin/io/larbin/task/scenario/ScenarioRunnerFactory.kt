package io.larbin.task.scenario

import io.larbin.api.scenario.entities.Scenario

class ScenarioRunnerFactory {
        fun createScenarioRunner(scenario: Scenario) : ScenarioRunner {
            return ScenarioRunner(scenario)
        }
}