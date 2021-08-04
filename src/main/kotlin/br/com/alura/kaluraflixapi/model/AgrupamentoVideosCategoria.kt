package br.com.alura.kaluraflixapi.model

import javax.persistence.*

@Entity
@Table(name = "categoria")
class AgrupamentoVideosCategoria(
        @Id
        val id: Long? = null,
        val titulo: String = "",
        val cor: String = "",

        @OneToMany
        @JoinColumn(name = "id_categoria")
        val videos: List<VideoCategoria> = ArrayList()

) {
}