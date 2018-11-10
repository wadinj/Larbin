package io.larbin.api.scenario

import org.springframework.data.mongodb.repository.MongoRepository

interface ScenarioRepository : MongoRepository<Scenario, Long>