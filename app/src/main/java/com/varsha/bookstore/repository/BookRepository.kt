package com.varsha.bookstore.repository

import com.varsha.bookstore.data.BookDetailResponseModel
import com.varsha.bookstore.data.BookResponseModel
import com.varsha.bookstore.mapper.BookDetailInfoMapper
import com.varsha.bookstore.mapper.BookInfoMapper
import com.varsha.bookstore.network.BookStoreApi
import com.varsha.bookstore.utility.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookRepository @Inject constructor(
    private val bookStoreApi: BookStoreApi,
    private val bookInfoMapper: BookInfoMapper,
    private val bookDetailInfoMapper: BookDetailInfoMapper,
) {
    /*
     * This is api call is used to get the data from the network
     * with the list of books and managed into resources
     */
    suspend fun getBookData(): Resource<List<BookResponseModel>> {
        val response = try {
            bookStoreApi.getBooksData().map {
                bookInfoMapper.mapToDomain(it)
            }
        } catch (e: Exception) {
            return Resource.Error("An unknown exception: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    /*
    * This is api call is used to get the data from the network
    * with the single books based on bookid
    */
    suspend fun getBookFromBookId(bookId: Int): Resource<BookDetailResponseModel> {
        val response = try {
            bookStoreApi.getBookFromBookId(bookId)
        } catch (e: Exception) {
            return Resource.Error("An unknown exception: ${e.localizedMessage}")
        }
        val result = bookDetailInfoMapper.mapToDomain(response)
        return Resource.Success(result)
    }
}
