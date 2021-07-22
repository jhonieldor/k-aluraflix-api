package br.com.alura.kaluraflixapi.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}