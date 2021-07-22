package br.com.alura.kaluraflixapi.controller

import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.service.VideoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/videos")
class VideoController(val videoService: VideoService) {

    @GetMapping
    fun listAll(@PageableDefault(size = 100) paginacao: Pageable): Page<Video> {
        return videoService.listAll(paginacao)
    }

    @GetMapping("/{id}")
    fun getVideo(@PathVariable id: Long): Video? {
        return videoService.getVideo(id)
    }

    @PostMapping
    @Transactional
    fun create(@RequestBody @Valid video: VideoCreate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Video> {
        val video = videoService.inserir(video)
        val uri = uriBuilder.path("/videos/${video.id}").build().toUri()
        return ResponseEntity.created(uri).body(video)
    }


    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid video: VideoUpdate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Video> {
        val video = videoService.atualizar(video)
        return ResponseEntity.ok(video)
    }


    @PatchMapping
    @Transactional
    fun patch(@RequestBody @Valid video: VideoUpdate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Video> {
        val video = videoService.atualizar(video)
        return ResponseEntity.ok(video)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable id: Long){
        videoService.remover(id)
    }
}