package br.com.alura.kaluraflixapi.infrastructure.security

import br.com.alura.kaluraflixapi.model.Usuario
import br.com.alura.kaluraflixapi.repository.UsuarioRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticacaoViaTokenFilter(
    val tokenService: TokenService,
    val repository: UsuarioRepository,
) : OncePerRequestFilter() {


    private fun autenticarCliente(token: String?) {
        val idUsuario = tokenService!!.getIdUsuario(token)
        val usuario: Usuario = repository!!.findById(idUsuario!!).get()
        val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            null
        } else token.substring(7, token.length)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recuperarToken(request)
        val valido = tokenService!!.isTokenValido(token)
        if (valido) {
            autenticarCliente(token)
        }
        filterChain.doFilter(request, response)
    }
}