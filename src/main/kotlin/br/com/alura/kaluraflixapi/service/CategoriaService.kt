package br.com.alura.kaluraflixapi.service

import br.com.alura.kaluraflixapi.dto.CategoriaCreate
import br.com.alura.kaluraflixapi.dto.CategoriaUpdate
import br.com.alura.kaluraflixapi.mapper.CategoriaCreateMapper
import br.com.alura.kaluraflixapi.mapper.CategoriaUpdateMapper
import br.com.alura.kaluraflixapi.model.AgrupamentoVideosCategoria
import br.com.alura.kaluraflixapi.model.Categoria
import br.com.alura.kaluraflixapi.repository.AgrupamentoVideosCategoriaRepository
import br.com.alura.kaluraflixapi.repository.CategoriaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoriaService(categoriaRepository: CategoriaRepository,
                       categoriaCreateMapper: CategoriaCreateMapper,
                       categoriaUpdateMapper: CategoriaUpdateMapper,
                       private val agrupamentoVideosCategoriaRepository: AgrupamentoVideosCategoriaRepository) :
        AbstractService<CategoriaCreate, CategoriaUpdate, Categoria>(
                notFoundMessage = "Categoria não encontrada",
                createMapper = categoriaCreateMapper,
                updateMapper = categoriaUpdateMapper,
                repository = categoriaRepository) {

    fun getVideosCategorias(paginacao: Pageable): Page<AgrupamentoVideosCategoria> {
        return agrupamentoVideosCategoriaRepository.findAll(paginacao)
    }

}