package br.com.alura.kaluraflixapi.repository

import br.com.alura.kaluraflixapi.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository: JpaRepository<Usuario, Long> {

    fun findByEmail(email: String): Optional<Usuario>
}