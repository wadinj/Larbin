package io.larbin.api.greeting

import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional(Transactional.TxType.MANDATORY)
interface GreetingRepository : JpaRepository<Greeting, Long>