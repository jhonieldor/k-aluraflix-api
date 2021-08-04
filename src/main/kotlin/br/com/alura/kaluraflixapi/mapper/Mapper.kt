package br.com.alura.kaluraflixapi.mapper

interface Mapper<M, T> {
    fun map(m: M): T
}