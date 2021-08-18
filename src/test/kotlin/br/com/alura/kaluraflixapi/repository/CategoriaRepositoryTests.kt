package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.Categoria
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class CategoriaRepositoryTests {

    val paginacao = Pageable.ofSize(100)

    @Autowired
    val categoriaRepository: CategoriaRepository? = null

    @Autowired
    val agrupamentoVideosCategoriaRepository: AgrupamentoVideosCategoriaRepository? = null

    @Autowired
    val videoRepository: VideoRepository? = null

    var categoria: Categoria? = Categoria(id = 0, titulo = "CATEGORIA TESTE", cor = "#FFF")

    @BeforeAll
    fun createCategoriaMustPass(){
        categoria = categoriaRepository?.save(categoria!!)
        assertNotNull(categoria)
    }

//    @AfterAll
//    fun removeCategoriaMustPass(){
//        val id = categoria!!.id
//
//        categoriaRepository?.deleteById(id!!)
//
//        val categoria = categoriaRepository?.findByIdOrNull(id!!)
//
//        assertNull(categoria)
//    }
//
//    @Test
//    fun updateCategoriaMustPass(){
//        val categoriaDb = categoriaRepository?.findById(categoria!!.id!!)?.orElseThrow()
//        println("categoriaDb: " + categoriaDb)
//        assertNotNull(categoriaDb)
//
//        val categoriaUpdate = Categoria(categoriaDb!!.id, "CATEGORIA ATUALIZADA", cor="#FFF5")
//
//        assertNotEquals(categoriaUpdate, categoriaDb)
//
//        categoria = categoriaRepository?.save(categoriaUpdate!!)
//        assertEquals(categoriaDb!!.id, categoria!!.id)
//    }
//
//
//
//    @Test
//    fun getAgrupamentosVideosCategorias(){
//        val agrupamentoPaginated = agrupamentoVideosCategoriaRepository?.findAll(paginacao)
//
//        println(agrupamentoPaginated!!.content.size)
//
//        agrupamentoPaginated!!.content.forEach { content ->
//            println("videos: " + content.videos.size)
//        }
//    }
}