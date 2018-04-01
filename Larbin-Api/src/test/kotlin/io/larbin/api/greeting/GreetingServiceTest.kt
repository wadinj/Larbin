package io.larbin.api.greeting

import org.junit.Assert
import org.junit.Test

class GreetingServiceTest {

    @Test
    fun `Should create a greeting with name and 1 as counter`() {
        val greetingService = GreetingService()
        val nameTest = "Robin"
        val actual = greetingService.createGreeting(nameTest)
        val expected = Greeting(1, nameTest)
        Assert.assertEquals(expected, actual)
    }
}