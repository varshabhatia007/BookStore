package com.varsha.bookstore.model

data class BookDetailResponse(
    val id: Int,
    val title: String,
    val isbn: String,
    val description: String,
    val price: Int,
    val currencyCode: String,
    val author: String
)
