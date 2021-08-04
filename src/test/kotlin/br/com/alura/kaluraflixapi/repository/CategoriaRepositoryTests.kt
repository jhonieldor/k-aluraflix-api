package br.com.alura.kaluraflixapi.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoriaRepositoryTests {

    val paginacao = Pageable.ofSize(100)

    @Autowired
    val categoriaRepository: CategoriaRepository? = null

    @Autowired
    val agrupamentoVideosCategoriaRepository: AgrupamentoVideosCategoriaRepository? = null

    @Autowired
    val videoRepository: VideoRepository? = null

    @Test
    fun getAgrupamentosVideosCategorias(){
        val agrupamentoPaginated = agrupamentoVideosCategoriaRepository?.findAll(paginacao)

        println(agrupamentoPaginated!!.content.size)

        agrupamentoPaginated!!.content.forEach { content ->
            println("videos: " + content.videos.size)
        }



    }
}