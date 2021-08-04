package br.com.alura.kaluraflixapi.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class CategoriaCreate(
        @field:NotNull(message = "Título não informado")
        @field:NotEmpty(message = "Título não informado")
        @field:Size(min = 5, max = 80, message = "O título deve ter entre 5 a 80 caracteres")
        val titulo: String = "",

        @field:NotNull(message = "Cor não informada")
        @field:NotEmpty(message = "Cor não informada")
        @field:Size(min = 2, max = 20, message = "O título deve ter entre 2 a 20 caracteres")
        @field:Pattern(regexp =  "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$", message = "A cor deve representar um valor hexadecimal válido")
        val cor: String = "",
) {
}