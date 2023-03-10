package com.varsha.bookstore.network

import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface BookStoreApi {
    /**
     * Get list of books
     * Return List of BookResponse
     */
    @GET("books")
    suspend fun getBooksData(): List<BookResponse>

    /**
     * Get book details based on bookid passed as parameter
     * Returns the book detail
     */
    @GET("book/{id}")
    suspend fun getBookFromBookId(@Path("id") id: Int): BookDetailResponse
}