package io.keepcoding.tareas.data.repository.mapper

interface Mapper<in M, out T> {

    fun map(input: M): T

}