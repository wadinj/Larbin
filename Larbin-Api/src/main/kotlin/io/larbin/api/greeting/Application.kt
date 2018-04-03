package io.larbin.api.greeting

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

private val logger = LoggerFactory.getLogger(Application::class.java)

fun main(args: Array<String>) {
    logger.info("Starting Larbin-API...")
    SpringApplication.run(Application::class.java, *args)
}