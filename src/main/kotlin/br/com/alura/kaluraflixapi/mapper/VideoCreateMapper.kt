package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.infrastructure.UrlValidator
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.repository.CategoriaRepository
import org.springframework.stereotype.Component

@Component
class VideoCreateMapper(val urlValidator: UrlValidator,
    val categoriaRepository: CategoriaRepository) : Mapper<VideoCreate, Video> {
    override fun map(create: VideoCreate): Video {

        urlValidator.checkUrl(create.url)

        val categoria = categoriaRepository.findById(create.idCategoria).orElseThrow { NotFoundException("Categoria do vídeo inexistente") }

        return Video(titulo = create.titulo,
                descricao = create.descricao,
                url = create.url,
                categoria = categoria)
    }
}