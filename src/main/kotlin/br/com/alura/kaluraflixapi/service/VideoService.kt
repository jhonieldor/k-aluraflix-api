package br.com.alura.kaluraflixapi.service

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.mapper.VideoCreateMapper
import br.com.alura.kaluraflixapi.mapper.VideoUpdateMapper
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.repository.VideoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.net.MalformedURLException
import java.net.URL

@Service
class VideoService(private val videoRepository: VideoRepository,
                   private val videoCreateMapper: VideoCreateMapper,
                   private val videoUpdateMapper: VideoUpdateMapper,
                   private val notFoundMessage: String = "Vídeo não encontrado") {

    fun listAll(pagination: Pageable): Page<Video> {
        return videoRepository.findAll(pagination)
    }

    fun getVideo(id: Long): Video? {
        return videoRepository.findById(id).orElseThrow { throw NotFoundException(notFoundMessage) }
    }

    fun inserir(videoCreate: VideoCreate): Video {
        val video = videoCreateMapper.map(videoCreate)
        checkUrl(video.url)
        return videoRepository.save(video)
    }

    fun atualizar(videoUpdate: VideoUpdate): Video {
        val video = videoUpdateMapper.map(videoUpdate)
        checkUrl(video.url)
        return videoRepository.save(video)
    }

    fun remover(id: Long) {
        videoRepository.findById(id).orElseThrow { throw NotFoundException(notFoundMessage) }
        videoRepository.deleteById(id)
    }

    private fun checkUrl(strUrl: String) {
        try {
            val url = URL(strUrl)
        } catch (e: MalformedURLException) {
            throw MalformedURLException("URL inválida")
        }
    }
}