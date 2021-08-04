package br.com.alura.kaluraflixapi.model

import javax.persistence.*

@Entity
@Table(name = "video")
class VideoCategoria(
        @Id
        val id: Long? = null,
        val titulo: String = "",
        val descricao: String = "",
        val url: String = "",
        @Column(name = "id_categoria")
        val idCategoria: Long? = null
) {

        override fun toString(): String {
                return "VideoCategoria(id=$id, titulo='$titulo', descricao='$descricao', url='$url', idCategoria=$idCategoria)"
        }
}