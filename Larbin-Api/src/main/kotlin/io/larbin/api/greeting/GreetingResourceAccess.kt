package io.larbin.api.greeting

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class GreetingResourceAccess(val greetingRepository: GreetingRepository) {

    fun retrieveGreeting(greetingId: Long): Greeting? {
        return greetingRepository.findOne(greetingId)
    }

    fun addGreeting(greeting: Greeting): GreetingResourceAccess {
        greetingRepository.save(greeting)
        return this
    }

    fun updateCity(greeting: Greeting): GreetingResourceAccess {
        val currentGreeting = greetingRepository.findOne(greeting.id)
        (currentGreeting != null)
        greetingRepository.save(currentGreeting)
        return this
    }
}