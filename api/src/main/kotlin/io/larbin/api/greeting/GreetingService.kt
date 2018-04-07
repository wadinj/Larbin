package io.larbin.api.greeting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GreetingService @Autowired constructor(@Autowired
                                             private val greetingResourceAccess: GreetingResourceAccess) {

    fun createGreeting(name: String): Greeting = greetingResourceAccess.addGreeting(Greeting(name))
}