package io.larbin.api.scenario

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EntityScan(basePackageClasses = arrayOf(Scenario::class))
@EnableMongoRepositories(basePackages = arrayOf("io.larbin.api"))
@ComponentScan
class LarbinConfig