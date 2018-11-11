package io.larbin.task.scenario

import io.larbin.api.scenario.ScenarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ScenarioWatcher(@Autowired val scenarioService: ScenarioService, @Autowired val scenarioRunnerFactory : ScenarioRunnerFactory) {

    @Scheduled(fixedDelay = 1000)
    fun getNewScenarios() {
        var scenarios = scenarioService.getAll()
        for(scenario in scenarios) {
            if(!scheduledScenarios.contains(scenario.name)) {
                var scenarioRunner = scenarioRunnerFactory.createScenarioRunner(scenario)
                scenarioRunner.run()
                scheduledScenarios.put(scenario.name, scenarioRunner)
            }
        }
    }

    companion object {
        private val scheduledScenarios = HashMap<String, Runnable>()
    }
}