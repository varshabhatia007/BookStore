package com.varsha.bookstore.viewmodel

import androidx.lifecycle.ViewModel
import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.model.BookResponse
import com.varsha.bookstore.repository.BookRepository
import com.varsha.bookstore.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    suspend fun getBooksData(): Resource<List<BookResponse>> {
        return bookRepository.getBookData()
    }

    suspend fun getBookDataFromId(bookId: Int): Resource<BookDetailResponse> {
        return bookRepository.getBookFromBookId(bookId)
    }
}