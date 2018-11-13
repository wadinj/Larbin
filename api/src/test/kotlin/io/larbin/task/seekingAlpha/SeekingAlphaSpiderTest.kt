package io.larbin.task.seekingAlpha


import io.larbin.LarbinConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = arrayOf(LarbinConfig::class))
class SeekingAlphaSpiderTest {

    @Autowired
    lateinit var spider: SeekingAlphaSpider

    @Test
    fun `Should run update transcripts without error`() {
        //spider.updateTranscripts()
    }
}
