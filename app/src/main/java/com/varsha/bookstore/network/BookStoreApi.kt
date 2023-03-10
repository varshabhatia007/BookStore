package com.varsha.bookstore.network

import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface BookStoreApi {
    @GET("books")
    suspend fun getBooksData(): List<BookResponse>

    @GET("book/{id}")
    suspend fun getBookFromBookId(@Path("id") id: Int): BookDetailResponse
}