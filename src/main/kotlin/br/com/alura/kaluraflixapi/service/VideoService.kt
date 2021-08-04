package br.com.alura.kaluraflixapi.service

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.mapper.VideoCreateMapper
import br.com.alura.kaluraflixapi.mapper.VideoUpdateMapper
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.repository.VideoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class VideoService(val videoRepository: VideoRepository,
                   videoCreateMapper: VideoCreateMapper,
                   videoUpdateMapper: VideoUpdateMapper) :
        AbstractService<VideoCreate, VideoUpdate, Video>(
                notFoundMessage = "Vídeo não encontrado",
                createMapper = videoCreateMapper,
                updateMapper = videoUpdateMapper,
                repository = videoRepository) {


    fun getByCategoria(nomeCategoria: String, paginacao: Pageable): Page<Video> {
        return videoRepository.findByCategoriaTitulo(nomeCategoria, paginacao)
    }

}