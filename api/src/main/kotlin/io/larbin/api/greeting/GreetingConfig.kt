package io.larbin.api.greeting

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = arrayOf(GreetingRepository::class))
@EntityScan(basePackageClasses = arrayOf(Greeting::class))
@EnableTransactionManagement
@ComponentScan
class GreetingConfig