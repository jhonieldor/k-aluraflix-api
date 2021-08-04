package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaRepository: JpaRepository<Categoria, Long> {

}