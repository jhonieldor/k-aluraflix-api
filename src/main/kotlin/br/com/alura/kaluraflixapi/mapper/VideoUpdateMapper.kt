package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.repository.VideoRepository
import org.springframework.stereotype.Component

@Component
class VideoUpdateMapper(private val videoRepository: VideoRepository) : Mapper<VideoUpdate, Video> {
    override fun map(t: VideoUpdate): Video {
        val dbVideo = videoRepository.findById(t.id!!).orElseThrow { throw NotFoundException("Vídeo não encontrado") }

        return Video(id = t.id,
                titulo = t.titulo ?: dbVideo.titulo,
                descricao = t.descricao ?: dbVideo.descricao,
                url = t.url ?: dbVideo.url)
    }

}