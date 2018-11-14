package io.larbin.api.scenario

import io.larbin.api.scenario.entities.Scenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository

interface ScenarioRepository : MongoRepository<Scenario, Long>