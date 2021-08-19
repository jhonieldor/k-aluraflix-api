package br.com.alura.kaluraflixapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class KAluraflixApiApplication

fun main(args: Array<String>) {
	runApplication<KAluraflixApiApplication>(*args)
}
