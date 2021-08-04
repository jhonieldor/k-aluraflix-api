package br.com.alura.kaluraflixapi.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class VideoCreate(
        @field:NotNull(message = "Título não informado")
        @field:NotEmpty(message = "Título não informado")
        @field:Size(min = 5, max = 80, message = "O título deve ter entre 5 a 80 caracteres")
        val titulo: String = "",

        @field:NotNull(message = "Descrição não informada")
        @field:NotEmpty(message = "Descrição não informada")
        @field:Size(min = 5, max = 80, message = "A descrição deve ter entre 5 a 80 caracteres")
        val descricao: String = "",


        @field:NotNull(message = "URL não informada")
        @field:NotEmpty(message = "URL não informada")
        val url: String = "",

        val idCategoria: Long = 1
) {


}