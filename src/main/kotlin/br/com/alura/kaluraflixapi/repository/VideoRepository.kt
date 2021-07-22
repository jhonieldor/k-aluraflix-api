package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.Video
import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository: JpaRepository<Video, Long> {
}