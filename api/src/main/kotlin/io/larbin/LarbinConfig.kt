package io.larbin

import io.larbin.api.scenario.entities.Scenario
import io.larbin.task.seekingAlpha.entities.EarningCall
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EntityScan(basePackageClasses = [Scenario::class, EarningCall::class])
@EnableMongoRepositories(basePackages = ["io.larbin"])
@ComponentScan
@EnableWebMvc
class LarbinConfig