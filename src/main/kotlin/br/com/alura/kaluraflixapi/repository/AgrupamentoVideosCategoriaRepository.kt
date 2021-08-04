package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.AgrupamentoVideosCategoria
import org.springframework.data.jpa.repository.JpaRepository

interface AgrupamentoVideosCategoriaRepository: JpaRepository<AgrupamentoVideosCategoria, Long> {
}