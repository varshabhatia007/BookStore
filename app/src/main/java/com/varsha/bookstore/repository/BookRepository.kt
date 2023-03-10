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
    /*
     * This is api call is used to get the data from the network
     * with the list of books and managed into resources
     */
    suspend fun getBookData(): Resource<List<BookResponse>> {
        val response = try {
            bookStoreApi.getBooksData()
        } catch (e: Exception) {
            return Resource.Error("An unknown exception: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    /*
    * This is api call is used to get the data from the network
    * with the single books based on bookid
    */
    suspend fun getBookFromBookId(bookId: Int): Resource<BookDetailResponse> {
        val response = try {
            bookStoreApi.getBookFromBookId(bookId)
        } catch (e: Exception) {
            return Resource.Error("An unknown exception: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}