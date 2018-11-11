package io.larbin.api.scenario


import io.larbin.LarbinConfig
import io.larbin.api.scenario.entities.Scenario
import io.larbin.api.scenario.entities.ScenarioConfig
import io.larbin.api.scenario.entities.ScenarioMapping
import io.larbin.api.scenario.entities.ScenarioRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = arrayOf(LarbinConfig::class))
class ScenarioControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `post and get it should retrieve it`() {
        val scenario = Scenario("WikipediaIncome", ScenarioRequest("https://fakeTarget.com"),
                listOf(ScenarioMapping("", "")), ScenarioConfig(true, 500, ""))
        testRestTemplate.postForObject("/scenario/", scenario, Scenario::class.java)
        val scenarioInserted = testRestTemplate.getForObject("/scenario", Array<Scenario>::class.java)
        testRestTemplate.delete("/scenario")
        assertThat(scenarioInserted).isNotNull()
        assertThat(scenario).isEqualTo(scenarioInserted[0])
    }
}
