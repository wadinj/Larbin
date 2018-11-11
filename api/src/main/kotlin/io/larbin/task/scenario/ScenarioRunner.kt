package io.larbin.task.scenario

import com.mongodb.util.JSON
import io.larbin.api.scenario.entities.Scenario
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ScenarioRunner(private val scenario: Scenario) : Runnable {

    override fun run() {

    }

    private fun getDocumentFromScenario() {
        var connection = Jsoup.connect(scenario.request?.url);
        var document : Document
        if(scenario.request?.headers != null) {
            connection.headers(scenario.request?.headers?.associate { header -> header.name to header.value })
        }
        when(scenario.request?.method?.toLowerCase()) {
            "get" -> document = connection.get()
        }
        for(mapping in scenario.mappings) {

        }


    }

}