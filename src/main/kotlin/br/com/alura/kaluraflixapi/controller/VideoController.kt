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
    fun listAll(@PageableDefault(size = 100) paginacao: Pageable, search: String? = null): Page<Video> {

        if (search != null) {
            return videoService.getByCategoria(search.uppercase(), paginacao)
        }

        return videoService.listAll(paginacao)
    }

    @GetMapping("/free")
    fun getListFree(@PageableDefault(size = 10) paginacao: Pageable, search: String? = null): Page<Video> {

        if (search != null) {
            return videoService.getByCategoria(search.uppercase(), paginacao)
        }

        return videoService.listAll(paginacao)
    }

    @GetMapping("/{id}")
    fun getVideo(@PathVariable id: Long): Video? {
        return videoService.getById(id)
    }

    @PostMapping
    @Transactional
    fun create(@RequestBody @Valid video: VideoCreate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Video> {
        val videoCreated = videoService.create(video)
        val uri = uriBuilder.path("/videos/${videoCreated.id}").build().toUri()
        return ResponseEntity.created(uri).body(videoCreated)
    }


    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid video: VideoUpdate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Video> {
        val videoUpdated = videoService.update(video)
        return ResponseEntity.ok(videoUpdated)
    }


    @PatchMapping
    @Transactional
    fun patch(@RequestBody @Valid video: VideoUpdate,
              uriBuilder: UriComponentsBuilder): ResponseEntity<Video> {
        val videoUpdated = videoService.update(video)
        return ResponseEntity.ok(videoUpdated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable id: Long) {
        videoService.remove(id)
    }


}