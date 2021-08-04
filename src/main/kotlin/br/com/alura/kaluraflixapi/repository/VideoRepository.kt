package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.Video
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository: JpaRepository<Video, Long> {

    fun findByCategoriaTitulo(nomeCategoria: String, paginacao: Pageable): Page<Video>

}