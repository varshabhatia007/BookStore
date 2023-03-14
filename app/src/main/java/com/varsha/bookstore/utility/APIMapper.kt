package com.varsha.bookstore.utility

interface ApiMapper<E, D> {
    fun mapToDomain(apiResponse: E): D
}