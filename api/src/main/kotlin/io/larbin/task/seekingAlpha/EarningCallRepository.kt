package io.larbin.task.seekingAlpha

import io.larbin.api.scenario.entities.Scenario
import io.larbin.task.seekingAlpha.entities.EarningCall
import org.springframework.data.mongodb.repository.MongoRepository

interface EarningCallRepository : MongoRepository<EarningCall, Long>