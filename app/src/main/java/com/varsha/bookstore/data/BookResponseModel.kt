package com.varsha.bookstore.data

data class BookResponseModel(
    val id: Int,
    val title: String,
    val price: Int,
    val currencyCode: String,
    val author: String
)
