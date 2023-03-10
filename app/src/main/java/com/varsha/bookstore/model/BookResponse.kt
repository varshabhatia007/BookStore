package com.varsha.bookstore.model

data class BookResponse(
    val id: Int,
    val title: String,
    val isbn: String,
    val price: Int,
    val currencyCode: String,
    val author: String
)
