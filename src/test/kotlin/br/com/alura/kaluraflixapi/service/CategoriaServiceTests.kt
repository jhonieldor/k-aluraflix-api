package br.com.alura.kaluraflixapi.service

import br.com.alura.kaluraflixapi.dto.CategoriaCreate
import br.com.alura.kaluraflixapi.dto.CategoriaUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.model.Categoria
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class CategoriaServiceTests {

    @Autowired
    val categoriaService: CategoriaService? = null


    var categoria: Categoria? = null

    val paginacao = Pageable.ofSize(100)

    @BeforeAll
    fun createCategoriaMustPass() {
        val categoriaCreate = CategoriaCreate("CATEGORIA TESTE", "#FFF")
        categoria = categoriaService?.create(categoriaCreate)

        assertNotNull(categoria)
    }

    @AfterAll
    fun removeCategoriaMustPass() {
        val id = categoria!!.id!!

        categoriaService?.remove(id)

        assertThrows(NotFoundException::class.java) {
            categoriaService?.getById(id);
        }

    }

    @Test
    fun updateCategoriaMustPass() {
        val categoriaUpdate = CategoriaUpdate(categoria!!.id, "CATEGORIA ATUALIZADA", "#FFF")

        val saved = categoriaService?.update(categoriaUpdate)

        assertNotNull(saved)
        assertEquals(saved!!.id, categoria!!.id)
    }

    @Test
    fun getCategoriaMustPass() {
        val categoriaDb = categoriaService?.getById(categoria!!.id!!)
        assertNotNull(categoriaDb!!.id!!)
    }

    @Test
    fun listCategoriasNotNullMustPass(){
        val categoriasPage = categoriaService?.getVideosCategorias(paginacao)
        assertNotNull(categoriasPage!!.content)
    }


}