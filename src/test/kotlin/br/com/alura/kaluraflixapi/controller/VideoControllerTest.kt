package br.com.alura.kaluraflixapi.controller

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.model.Video
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
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
class VideoControllerTest {

    @Autowired
    val mockMvcHelper: MockMvcTestsHelper? = null

    @Autowired
    val mockMvc: MockMvc? = null

    val endpoint = "/videos"

    val gson = GsonBuilder().setPrettyPrinting().create()

    var video: Video? = null

    @BeforeAll
    fun createVideoMustPass(){
        val json  = mockMvcHelper!!.postResponseStatusCreated201(mockMvc!!, endpoint, getJsonCreateOk())
        video = gson.fromJson(json, Video::class.java)
        assertNotNull(video)
        assertNotNull(video!!.categoria)
        assertEquals(1, video!!.categoria!!.id)
    }

    @AfterAll
    fun deleteVideoMustPass(){
        mockMvcHelper!!.deleteResponseNoContent204(mockMvc!!, "$endpoint/${video!!.id!!}")

        val PI = 3.144
        val POW = 300

        val f = Math.sqrt(PI) * POW

    }



    @Test
    fun createVideoMustResponseBadRequest400(){
        mockMvcHelper!!.putResponseBadRequest400(mockMvc!!, endpoint, getJsonCreateInvalidNullData())
        mockMvcHelper!!.putResponseBadRequest400(mockMvc!!, endpoint, getJsonCreateInvalidEmptyData())
        mockMvcHelper!!.putResponseBadRequest400(mockMvc!!, endpoint, getJsonCreateInvalidURL())
    }


    @Test
    fun updateVideoMustResponseStatusOK200(){
        mockMvcHelper!!.putResponseStatusOK200(mockMvc!!, endpoint, getJsonUpdateOK())
    }

    @Test
    fun updateVideoParcialUpdateMustResponseStatusOK200(){
        mockMvcHelper!!.putResponseStatusOK200(mockMvc!!, endpoint, getJsonUpdateParcialOk())
    }

    @Test
    fun updateVideoWithInvalidURLMustResponseBadRequest400(){
        mockMvcHelper!!.putResponseBadRequest400(mockMvc!!, endpoint, getJsonUpdateInvalidURL())
    }

    @Test
    fun updateVideoEmptyDataMustResponseBadRequest400(){
        mockMvcHelper!!.putResponseBadRequest400(mockMvc!!, endpoint, getJsonCreateInvalidEmptyData())
    }

    @Test
    fun getVideosPagesMustResponseOK200(){
        mockMvcHelper!!.getResponseStatusOK200(mockMvc!!, endpoint)
        mockMvcHelper!!.getResponseStatusOK200(mockMvc!!, "$endpoint?search=LIVRE")
    }

    @Test
    fun getVideoByIdMustResponseOK200(){
        println("video: " + video)
        mockMvcHelper!!.getResponseStatusOK200(mockMvc!!, "$endpoint/${video!!.id}")
    }

    fun getJsonCreateOk(): String {
        return gson.toJson(VideoCreate(titulo = "TESTE VIDEO CRIADO", descricao = "Teste automatizado de video criado", url = "http://localhost:8080"))
    }

    fun getJsonCreateInvalidURL(): String {
        return gson.toJson(VideoCreate(titulo = "TESTE VIDEO INVALIDO", descricao = "Teste automatizado de video invalido", url = "banana"))
    }

    fun getJsonCreateInvalidEmptyData(): String {
        return gson.toJson(VideoCreate(titulo = "TESTE VIDEO INVALIDO", descricao = "", url = ""))
    }

    fun getJsonCreateInvalidNullData(): String {
        return "{\n" +
                "    \"titulo\": \"Video 6\"\n" +
                "}"
    }


    fun getJsonUpdateOK(): String {

        return gson.toJson(VideoUpdate(
                id = video!!.id,
                titulo = "TESTE VIDEO CRIADO",
                descricao = "Teste automatizado de video criado",
                url = "http://localhost:8080"))
    }

    fun getJsonUpdateInvalidURL(): String {
        return gson.toJson(VideoUpdate(
                id = video!!.id,
                titulo = "TESTE VIDEO CRIADO",
                descricao = "Teste automatizado de video criado",
                url = "banana"))
    }


    fun getJsonUpdateParcialOk(): String {
        return "{\n" +
                "    \"id\": \"${video!!.id}\",\n" +
                "    \"titulo\": \"Video 6\"\n" +
                "}"
    }
}