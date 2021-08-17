package br.com.alura.kaluraflixapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile

@SpringBootApplication
class KAluraflixApiApplication

fun main(args: Array<String>) {
	runApplication<KAluraflixApiApplication>(*args)
}
