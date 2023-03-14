package com.varsha.bookstore.viewmodel

import androidx.lifecycle.ViewModel
import com.varsha.bookstore.data.BookDetailResponseModel
import com.varsha.bookstore.data.BookResponseModel
import com.varsha.bookstore.repository.BookRepository
import com.varsha.bookstore.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    suspend fun getBooksData(): Resource<List<BookResponseModel>> {
        return bookRepository.getBookData()
    }

    suspend fun getBookDataFromId(bookId: Int): Resource<BookDetailResponseModel> {
        return bookRepository.getBookFromBookId(bookId)
    }
}