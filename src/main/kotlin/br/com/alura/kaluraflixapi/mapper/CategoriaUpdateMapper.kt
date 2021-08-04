package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.CategoriaUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.model.Categoria
import br.com.alura.kaluraflixapi.repository.CategoriaRepository
import org.springframework.stereotype.Component

@Component
class CategoriaUpdateMapper(val categoriaRepository: CategoriaRepository) : Mapper<CategoriaUpdate, Categoria> {
    override fun map(m: CategoriaUpdate): Categoria {

        val dbCategoria: Categoria = categoriaRepository.findById(m.id!!).orElseThrow { throw NotFoundException("Categoria inexistente") }

        return Categoria(id = m.id,
                titulo = m.titulo ?: dbCategoria.titulo.uppercase(),
                cor = m.cor ?: dbCategoria.cor)
    }
}