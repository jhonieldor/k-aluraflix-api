package br.com.alura.kaluraflixapi.exception

import java.lang.RuntimeException

class NotFoundException(message: String): RuntimeException(message)  {
}