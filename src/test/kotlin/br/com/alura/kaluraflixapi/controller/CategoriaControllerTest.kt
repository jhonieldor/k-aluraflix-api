package br.com.alura.kaluraflixapi.controller

import br.com.alura.kaluraflixapi.model.Categoria
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    val mockMvcHelper: MockMvcTestsHelper? = null

    @Autowired
    val mockMvc: MockMvc? = null

    var categoria: Categoria? = null

    val endpoint = "/categorias"

    val gson = GsonBuilder().setPrettyPrinting().create()

    @BeforeAll
    fun createCategoriaMustPass() {
        val jsonResponse = mockMvcHelper!!.postResponseStatusCreated201(mockMvc!!, endpoint, getJsonCreateOk())
        categoria = gson.fromJson(jsonResponse, Categoria::class.java)
        assertNotNull(categoria)
    }

    @AfterAll
    fun deleteCategoriaMustPass() {
        mockMvcHelper!!.deleteResponseNoContent204(mockMvc!!, "$endpoint/${categoria!!.id}")
    }

    @Test
    fun postCategoriaMustResponse400(){
        mockMvcHelper!!.postResponseBadRequest400(mockMvc!!, endpoint, getJsonCreateInalid())
    }

    @Test
    fun putCategoriaMustResponse200(){
        mockMvcHelper!!.putResponseStatusOK200(mockMvc!!, endpoint, getJsonUpdateOk())
    }

    @Test
    fun putCategoriaMustResponse400(){
        mockMvcHelper!!.putResponseBadRequest400(mockMvc!!, endpoint, getJsonUpdateInvalid())
    }

    @Test
    fun getCategoriaByIdMustResponse200OK(){
        mockMvcHelper!!.getResponseStatusOK200(mockMvc!!, "$endpoint/${categoria!!.id!!}")
    }

    @Test
    fun getCategoriasMustResponse200OK(){
        mockMvcHelper!!.getResponseStatusOK200(mockMvc!!, endpoint)
        mockMvcHelper!!.getResponseStatusOK200(mockMvc!!, "$endpoint/agrupamentos")
    }



    fun getJsonCreateOk(): String {
        return "{\n" +
                "\t\"titulo\": \"TESTE CRIACAO\",\n" +
                "\t\"cor\": \"#1703fc\"\n" +
                "}"
    }

    fun getJsonCreateInalid(): String {
        return "{\n" +
                "\t\"titulo\": \"TESTE CRIACAO\",\n" +
                "\t\"cor\": \"banana\"\n" +
                "}"
    }

    fun getJsonUpdateOk(): String {
        return "{\n" +
                "\t\"id\": \"${categoria!!.id}\",\n" +
                "\t\"cor\": \"#FFF\"\n" +
                "}"
    }

    fun getJsonUpdateInvalid(): String {
        return "{\n" +
                "\t\"cor\": \"#FFF\"\n" +
                "}"
    }
}