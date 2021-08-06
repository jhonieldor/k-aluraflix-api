package br.com.alura.kaluraflixapi.service

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.model.Categoria
import br.com.alura.kaluraflixapi.model.Video
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Pageable
import java.net.MalformedURLException

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VideoServiceTests {

    @Autowired
    val videoService: VideoService? = null

    var video: Video? = null

    val paginacao = Pageable.ofSize(100)

    @BeforeAll
    fun createVideoMustPass() {
        video = videoService!!.create(VideoCreate("Video Teste Automatizado", "Vídeo criado a partir de teste automatizado", "http://localhost:8080"))

        assertNotNull(video)
        assertNotNull(video!!.categoria)
        assertEquals(1, video!!.categoria!!.id)

    }

    @AfterAll
    fun removeVideoMustPass() {
        val id = video!!.id!!
        videoService!!.remove(id)
        assertThrows(NotFoundException::class.java) {
            videoService!!.getById(id)
        }
    }

    @Test
    fun getVideoNotNullMustPass() {
        val videoDb = videoService!!.getById(video!!.id!!)
        assertNotNull(videoDb)
    }

    @Test
    fun updateVideoMustPass() {
        val videoDb = videoService!!.getById(video!!.id!!)

        assertNotNull(videoDb)

        video = videoService!!.update(VideoUpdate(video!!.id, "Teste de atualização", "Teste"))
        assertNotNull(video)
        assertNotNull(video!!.categoria)
        assertEquals(1, video!!.categoria!!.id)
        assertEquals(videoDb!!.id!!, video!!.id!!)
    }

    @Test
    fun getVideoByTituloCategoriaMustPass() {
        val videosPage = videoService!!.getByCategoria("LIVRE", paginacao)
        assertTrue(videosPage.content.isNotEmpty())
    }

    @Test
    fun createVideoWithInvalidURLMustThrows() {
        assertThrows(MalformedURLException::class.java) {
            videoService!!.create(VideoCreate("Teste de validação", "Teste de validação", "banana"))
            videoService!!.update(VideoUpdate(video!!.id!!, "Teste de validação", "Teste de validação", "banana"))
        }
    }
}