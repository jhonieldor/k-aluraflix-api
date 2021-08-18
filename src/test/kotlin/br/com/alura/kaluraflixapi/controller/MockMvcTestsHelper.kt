package br.com.alura.kaluraflixapi.controller

import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URI

@Component
@ActiveProfiles("test")
class MockMvcTestsHelper {

    fun postResponseStatusCreated201(mockMvc: MockMvc, endpoint: String, json: String): String {
        val uri = URI(endpoint)
        return mockMvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andReturn().response.contentAsString
    }

    fun putResponseStatusOK200(mockMvc: MockMvc, endpoint: String, json: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    fun postResponseBadRequest400(mockMvc: MockMvc, endpoint: String, json: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(400))
    }

    fun putResponseBadRequest400(mockMvc: MockMvc, endpoint: String, json: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(400))
    }

    fun postOrPutResponseInternalServerError500(mockMvc: MockMvc, endpoint: String, json: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(500))
    }

    fun getResponseStatusOK200(mockMvc: MockMvc, endpoint: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    fun getByIdResponseNotFound204(mockMvc: MockMvc, endpoint: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(404))
    }


    fun deleteResponseNoContent204(mockMvc: MockMvc, endpoint: String) {
        val uri = URI(endpoint)
        mockMvc.perform(
            MockMvcRequestBuilders
                .request(HttpMethod.DELETE, uri)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().`is`(204))
    }
}