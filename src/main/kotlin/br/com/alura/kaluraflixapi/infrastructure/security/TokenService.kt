package br.com.alura.kaluraflixapi.infrastructure.security

import br.com.alura.kaluraflixapi.model.Usuario
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService {

    @Value("\${forum.jwt.expiration}")
    private val expiration: String? = null

    @Value("\${forum.jwt.secret}")
    private val secret: String? = null

    fun gerarToken(authentication: Authentication): String? {
        val logado: Usuario = authentication.principal as Usuario
        val hoje = Date()
        val dataExpiracao = Date(hoje.time + expiration!!.toLong())
        return Jwts.builder()
            .setIssuer("API da AluraFlix")
            .setSubject(logado.id.toString())
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isTokenValido(token: String?): Boolean {
        println("isTokenValido(token: String?)")
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getIdUsuario(token: String?): Long? {
        println("getIdUsuario(token: String?)")
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return claims.subject.toLong()
    }
}