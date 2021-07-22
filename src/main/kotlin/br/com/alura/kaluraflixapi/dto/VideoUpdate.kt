package br.com.alura.kaluraflixapi.dto

import javax.validation.constraints.*

data class VideoUpdate(
        @field:NotNull(message = "id não informado")
        val id: Long? = null,

        @field:Size(min = 5, max = 80, message = "O título deve ter entre 5 a 80 caracteres")
        val titulo: String? = null,

        @field:Size(min = 5, max = 80, message = "A descrição deve ter entre 5 a 80 caracteres")
        val descricao: String? = null,

        val url: String? = null
)