package br.com.alura.kaluraflixapi.service

import br.com.alura.kaluraflixapi.dto.VideoUpdate
import br.com.alura.kaluraflixapi.exception.NotFoundException
import br.com.alura.kaluraflixapi.mapper.Mapper
import br.com.alura.kaluraflixapi.model.Video
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * CREATE-> generics for CreateMappers
 * UPDATE-> generics for UpdateMappers
 * T -> generics for Entities mapped with database
 */
open abstract class AbstractService<CREATE, UPDATE, T>(private val notFoundMessage: String,
                                             private val createMapper: Mapper<CREATE, T>,
                                             private val updateMapper: Mapper<UPDATE, T>,
                                             private val repository: JpaRepository<T, Long>) {
    fun listAll(paginacao: Pageable): Page<T> {
        return repository.findAll(paginacao)
    }

    fun getById(id: Long): T? {
        return repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun remove(id: Long) {
        repository.findById(id).orElseThrow { throw NotFoundException(notFoundMessage) }
        repository.deleteById(id)
    }

    fun create(objectCreate: CREATE): T {
        val objectToSave: T = createMapper.map(objectCreate)
        return repository.save(objectToSave)
    }

    fun update(objectUpdate: UPDATE): T {
        val objectToSave: T = updateMapper.map(objectUpdate)
        return repository.save(objectToSave)
    }
}