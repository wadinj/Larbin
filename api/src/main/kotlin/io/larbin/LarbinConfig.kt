package io.larbin

import io.larbin.api.scenario.entities.Scenario
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EntityScan(basePackageClasses = [Scenario::class])
@EnableMongoRepositories(basePackages = ["io.larbin"])
@ComponentScan
class LarbinConfig