package io.larbin.api.greeting

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(GreetingConfig::class, SwaggerConfiguration::class))
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WebAppConfiguration
class GreetingResourceAccessTest {

    @Autowired
    lateinit var service: GreetingResourceAccess

    @Test
    fun `'retrieve' greeting with an inconsistent id should returns null`() {
        assertThat(service.retrieveGreeting(-99)).isNull()
    }

    @Test
    fun `'create' greeting should insert in database with id auto-increment`() {
        val greetingName = "Test"
        service.addGreeting(Greeting(greetingName))
        val greetingRetrieve = service.retrieveGreeting(1)
        assertThat(greetingRetrieve?.id).isEqualTo(1)
        assertThat(greetingRetrieve?.content).isEqualTo(greetingName)
    }
}