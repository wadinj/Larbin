package io.larbin.api.scenario


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScenarioControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testHelloController() {
        val scenario = Scenario(2L, "https://fakeTarget.com", listOf(""), false)
        testRestTemplate.postForObject("/scenario/", scenario, Scenario::class.java)
        val scenarioInserted = testRestTemplate.getForObject("/scenario/2", Scenario::class.java)
        testRestTemplate.delete("/scenario/2")
        assertThat(scenarioInserted).isNotNull()
        assertThat(scenario).isEqualTo(scenarioInserted)
    }
}
