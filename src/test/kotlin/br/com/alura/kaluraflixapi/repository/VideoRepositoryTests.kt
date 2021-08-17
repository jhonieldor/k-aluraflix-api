package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.Categoria
import br.com.alura.kaluraflixapi.model.Video
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class VideoRepositoryTests {

    @Autowired
    val videoRepository: VideoRepository? = null

    @Autowired
    val categoriaRepository: CategoriaRepository? = null

    val paginacao = Pageable.ofSize(100)

    var video: Video? = null

    @BeforeAll
    fun createMustPass() {
        val categoria: Categoria? = categoriaRepository?.findById(4)?.get()

        assertNotNull(categoria)

        video = Video(titulo = "Teste de inclusão", descricao = "Teste automatizado de inclusão", categoria = categoria!!)

        val saved = videoRepository?.save(video!!)

        assertNotNull(saved)
    }

    @AfterAll
    fun deleteMustPass() {

        assertNotNull(video)
        videoRepository?.deleteById(video!!.id!!)

        val dbVideo: Video? = videoRepository?.findByIdOrNull(video!!.id!!)
        assertNull(dbVideo)

    }


    @Test
    fun updateVideoMustPass() {

        val categoria: Categoria? = categoriaRepository?.findById(1)?.get()
        assertNotNull(categoria)

        val dbVideo: Video? = videoRepository?.findById(video!!.id!!)?.get()
        assertNotNull(dbVideo)

        val videoToUpdate = Video(titulo = dbVideo!!.titulo, descricao = "Teste automatizado de atualização", categoria = categoria!!)

        val saved = videoRepository?.save(videoToUpdate)

        assertNotNull(saved)
    }



    @Test
    fun getVideosByNomeCategoriaTestMustPass() {
        val videosGet = videoRepository!!.findByCategoriaTitulo("LIVRE", paginacao)
        assert(videosGet.content.isNotEmpty())
    }

    @Test
    fun getVideosByNomeCategoriaTestMustEmpty() {
        val videosGet = videoRepository!!.findByCategoriaTitulo("XXX", paginacao);
        assert(videosGet.content.isEmpty())
    }


}