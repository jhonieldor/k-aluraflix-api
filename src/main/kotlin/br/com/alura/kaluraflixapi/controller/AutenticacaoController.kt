package br.com.alura.kaluraflixapi.controller

import br.com.alura.kaluraflixapi.dto.LoginForm
import br.com.alura.kaluraflixapi.dto.TokenDto
import br.com.alura.kaluraflixapi.infrastructure.security.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Profile(value = ["prod", "test"])
class AutenticacaoController {

    @Autowired
    private val authManager: AuthenticationManager? = null

    @Autowired
    private val tokenService: TokenService? = null

    @PostMapping
    fun autenticar(@RequestBody form: @Valid LoginForm?): ResponseEntity<TokenDto?>? {
        println("autenticar(@RequestBody form: @Valid LoginForm?)")
        val dadosLogin: UsernamePasswordAuthenticationToken? = form!!.converter()
        return try {
            val authentication = authManager!!.authenticate(dadosLogin)
            val token: String? = tokenService?.gerarToken(authentication)
            ResponseEntity.ok<TokenDto?>(TokenDto(token!!, "Bearer"))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build<TokenDto?>()
        }
    }
}