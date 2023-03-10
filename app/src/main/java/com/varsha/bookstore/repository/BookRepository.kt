package com.varsha.bookstore.repository

import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.model.BookResponse
import com.varsha.bookstore.network.BookStoreApi
import com.varsha.bookstore.utility.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookRepository @Inject constructor(
    private val bookStoreApi: BookStoreApi
) {
    suspend fun getBookDataResponse(): Resource<List<BookResponse>> {
        val response = try {
            bookStoreApi.getBooksData()
        } catch (e: Exception) {
            return Resource.Error("An unknown exception: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getBookFromBookId(bookId: Int): Resource<BookDetailResponse> {
        val response = try {
            bookStoreApi.getBookFromBookId(bookId)
        } catch (e: Exception) {
            return Resource.Error("An unknown exception: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}