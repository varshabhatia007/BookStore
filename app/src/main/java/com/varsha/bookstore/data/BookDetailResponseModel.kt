package com.varsha.bookstore.data

data class BookDetailResponseModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val currencyCode: String,
    val author: String,
    val isbn: String
)
