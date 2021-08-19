package br.com.alura.kaluraflixapi.dto

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

data class LoginForm(
    private var email: String? = null,
    private var senha: String? = null
) {

    fun converter(): UsernamePasswordAuthenticationToken? {
        return UsernamePasswordAuthenticationToken(email, senha)
    }
}