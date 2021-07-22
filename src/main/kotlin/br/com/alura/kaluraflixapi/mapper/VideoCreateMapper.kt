package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.model.Video
import org.springframework.stereotype.Component

@Component
class VideoCreateMapper : Mapper<VideoCreate, Video> {
    override fun map(create: VideoCreate): Video {
        return Video(titulo = create.titulo,
                descricao = create.descricao,
                url = create.url)
    }
}