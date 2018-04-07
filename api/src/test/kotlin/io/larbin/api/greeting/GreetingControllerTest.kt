package io.larbin.api.greeting


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testHelloController() {
        testRestTemplate.postForEntity("/greeting", Greeting("World"), Greeting::class.java)
        val result = testRestTemplate.getForEntity("/greeting?id=1", Greeting::class.java)
        assertThat(result).isNotNull()
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(Greeting("World")).isEqualTo(result.body)
    }
}