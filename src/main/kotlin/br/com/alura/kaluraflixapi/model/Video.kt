package br.com.alura.kaluraflixapi.model

import javax.persistence.*

@Entity
data class Video(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val titulo: String = "",
        val descricao: String = "",
        val url: String = "",
        @ManyToOne
        @JoinColumn(name = "id_categoria", referencedColumnName = "id")
        val categoria: Categoria? = null
) {


}