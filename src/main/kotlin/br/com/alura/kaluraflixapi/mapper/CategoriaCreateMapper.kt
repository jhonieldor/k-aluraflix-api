package br.com.alura.kaluraflixapi.mapper

import br.com.alura.kaluraflixapi.dto.CategoriaCreate
import br.com.alura.kaluraflixapi.model.Categoria
import org.springframework.stereotype.Component

@Component
class CategoriaCreateMapper : Mapper<CategoriaCreate, Categoria> {
    override fun map(m: CategoriaCreate): Categoria {
        return Categoria(titulo = m.titulo.uppercase(), cor = m.cor)
    }
}