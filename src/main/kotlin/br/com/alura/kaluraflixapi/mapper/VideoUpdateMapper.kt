package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.infrastructure.validators.UrlValidator
import br.com.alura.kaluraflixapi.model.Categoria
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.repository.CategoriaRepository
import br.com.alura.kaluraflixapi.repository.VideoRepository
import org.springframework.stereotype.Component

@Component
class VideoUpdateMapper(private val videoRepository: VideoRepository,
                        private val categoriaRepository: CategoriaRepository,
                        private val urlValidator: UrlValidator
) : Mapper<VideoUpdate, Video> {
    override fun map(m: VideoUpdate): Video {
        val dbVideo = videoRepository.findById(m.id!!).orElseThrow { throw NotFoundException("Vídeo não encontrado") }

        if (m.url != null) {
            urlValidator.checkUrl(m.url)
        }
        var categoria: Categoria? = null

        if (m.idCategoria != null) {
            categoria = categoriaRepository.findById(m.idCategoria).orElseThrow { throw NotFoundException("Categoria inexistente") }
        }


        return Video(id = m.id,
                titulo = m.titulo ?: dbVideo.titulo,
                descricao = m.descricao ?: dbVideo.descricao,
                url = m.url ?: dbVideo.url,
                categoria = categoria ?: dbVideo.categoria)
    }

}