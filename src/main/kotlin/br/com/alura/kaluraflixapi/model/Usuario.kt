package br.com.alura.kaluraflixapi.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String,
    @ManyToMany(fetch = FetchType.EAGER)
    private var perfis: List<Perfil?>? = ArrayList()
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return perfis
    }

    override fun getPassword(): String {
        return senha
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}