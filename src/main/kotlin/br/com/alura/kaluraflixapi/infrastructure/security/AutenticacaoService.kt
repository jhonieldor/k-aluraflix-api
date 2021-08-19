package br.com.alura.kaluraflixapi.infrastructure.security

import br.com.alura.kaluraflixapi.model.Usuario
import br.com.alura.kaluraflixapi.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutenticacaoService: UserDetailsService {

    @Autowired
    private val repository: UsuarioRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val usuario: Optional<Usuario> = repository!!.findByEmail(username)
        if (usuario.isPresent()) {
            return usuario.get()
        }
        throw UsernameNotFoundException("Dados inválidos!")
    }
}