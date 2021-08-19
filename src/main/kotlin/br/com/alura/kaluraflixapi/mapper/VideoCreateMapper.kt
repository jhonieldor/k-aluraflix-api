package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.infrastructure.validators.UrlValidator
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.repository.CategoriaRepository
import org.springframework.stereotype.Component

@Component
class VideoCreateMapper(
    val urlValidator: UrlValidator,
    val categoriaRepository: CategoriaRepository
) : Mapper<VideoCreate, Video> {
    override fun map(m: VideoCreate): Video {

        urlValidator.checkUrl(m.url)

        val categoria = categoriaRepository.findById(m.idCategoria)
            .orElseThrow { NotFoundException("Categoria do vídeo inexistente") }

        return Video(
            titulo = m.titulo,
            descricao = m.descricao,
            url = m.url,
            categoria = categoria
        )
    }
}