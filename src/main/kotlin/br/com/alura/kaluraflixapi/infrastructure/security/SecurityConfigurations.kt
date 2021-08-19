package br.com.alura.kaluraflixapi.infrastructure.security

import br.com.alura.kaluraflixapi.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
@Profile(value = ["prod", "test"])
class SecurityConfigurations : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var autenticacaoService: AutenticacaoService

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.GET, "/videos/free").permitAll()
            .antMatchers(HttpMethod.DELETE, "/videos/*").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/categorias/*").hasRole("ADMIN")
            .anyRequest().authenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(
                AutenticacaoViaTokenFilter(tokenService!!, usuarioRepository!!),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    @Autowired
    private val tokenService: TokenService? = null

    @Autowired
    private val usuarioRepository: UsuarioRepository? = null

    @Bean
    @Throws(Exception::class)
    override fun authenticationManager(): AuthenticationManager? {
        return super.authenticationManager()
    }

    @Throws(java.lang.Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(this.autenticacaoService).passwordEncoder(passwordEncoder())
    }


    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}