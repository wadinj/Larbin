package io.larbin.api.scenario

import io.larbin.task.seekingAlpha.EarningCallRepository
import io.larbin.task.seekingAlpha.SeekingAlphaSpider
import io.larbin.task.seekingAlpha.entities.EarningCall
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/earningCalls")
class SeekingAlphaController(@Autowired private val earningCallRepository: EarningCallRepository, @Autowired private val spider: SeekingAlphaSpider) {

    @GetMapping()
    fun getAll(): List<EarningCall> = earningCallRepository.findAll()

    @GetMapping("/test")
    fun test(): List<EarningCall> {
        spider.updateTranscripts()
        return earningCallRepository.findAll()
    }
}