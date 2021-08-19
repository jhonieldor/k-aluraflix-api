package br.com.alura.kaluraflixapi.infrastructure.validators

import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL

@Component
class UrlValidator {

    fun checkUrl(strUrl: String): URL {
        try {
            return URL(strUrl)
        } catch (e: MalformedURLException) {
            throw MalformedURLException("URL inválida")
        }
    }
}