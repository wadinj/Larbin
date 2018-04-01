package io.larbin.api.greeting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GreetingService @Autowired constructor() {

    fun createGreeting(name: String): Greeting = Greeting(1, name)
}