package br.com.alura.kaluraflixapi.controller

import br.com.alura.kaluraflixapi.dto.CategoriaCreate
import br.com.alura.kaluraflixapi.dto.CategoriaUpdate
import br.com.alura.kaluraflixapi.dto.VideoCreate
import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.model.AgrupamentoVideosCategoria
import br.com.alura.kaluraflixapi.model.Categoria
import br.com.alura.kaluraflixapi.model.Video
import br.com.alura.kaluraflixapi.service.CategoriaService
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
@RequestMapping("/categorias")
class CategoriaController(val categoriaService: CategoriaService) {


    @GetMapping("/agrupamentos")
    fun getVideosCategorias(@PageableDefault(size = 100) paginacao: Pageable): Page<AgrupamentoVideosCategoria> {
        return categoriaService.getVideosCategorias(paginacao)
    }

    @GetMapping
    fun listAll(@PageableDefault(size = 100) paginacao: Pageable): Page<Categoria> {
        return categoriaService.listAll(paginacao)
    }

    @GetMapping("/{id}")
    fun getVideo(@PathVariable id: Long): Categoria? {
        return categoriaService.getById(id)
    }


    @PostMapping
    @Transactional
    fun create(@RequestBody @Valid categoria: CategoriaCreate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Categoria> {
        val video = categoriaService.create(categoria)
        val uri = uriBuilder.path("/categorias/${video.id}").build().toUri()
        return ResponseEntity.created(uri).body(video)
    }


    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid categoria: CategoriaUpdate,
               uriBuilder: UriComponentsBuilder): ResponseEntity<Categoria> {
        val categoriaUpdated = categoriaService.update(categoria)
        return ResponseEntity.ok(categoriaUpdated)
    }


    @PatchMapping
    @Transactional
    fun patch(@RequestBody @Valid categoria: CategoriaUpdate,
              uriBuilder: UriComponentsBuilder): ResponseEntity<Categoria> {
        val categoriaUpdated = categoriaService.update(categoria)
        return ResponseEntity.ok(categoriaUpdated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable id: Long){
        categoriaService.remove(id)
    }
}