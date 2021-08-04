package br.com.alura.kaluraflixapi.infrastructure

import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL

@Component
class UrlValidator {

    fun checkUrl(strUrl: String) {
        try {
            val url = URL(strUrl)
        } catch (e: MalformedURLException) {
            throw MalformedURLException("URL inválida")
        }
    }
}