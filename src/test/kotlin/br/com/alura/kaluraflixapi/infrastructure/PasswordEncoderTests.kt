package br.com.alura.kaluraflixapi.infrastructure

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class PasswordEncoderTests {

    /**
     * --insert into perfil(nome)
    --    values
    --    ('ROLE_ADMIN'),
    --    ('ROLE_USER');
    --
    --insert into usuario(nome, email, senha)
    --    values()

     */

    @Test
    fun econdePassword(){
        var senha = "admin_pass"
        val encoder = BCryptPasswordEncoder()
        println(encoder.encode(senha).toString())
        //$2a$10$pd9kZ0vZcpzawd.IkS3vXObitqJs447NAhYO7T3A9ShRzJGCIVQhi

        senha = "user_pass"
        println(encoder.encode(senha).toString())
        //$2a$10$0DVfHvcWzxFh1UEBGvK8Kutzi9HPJnnbXDKTZ3wSolZzzkKcYPbgu
    }

}