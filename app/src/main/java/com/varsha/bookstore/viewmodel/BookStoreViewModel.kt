package com.varsha.bookstore.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    var isLoading = mutableStateOf(false)
    private var _getBookData: MutableLiveData<List<BookResponse>> =
        MutableLiveData<List<BookResponse>>()
    var getBookData: LiveData<List<BookResponse>> = _getBookData

    var isLoadingBook = mutableStateOf(false)
    private var _getBookDataFromId: MutableLiveData<BookDetailResponse> =
        MutableLiveData<BookDetailResponse>()
    var getBookDataFromId: LiveData<BookDetailResponse> = _getBookDataFromId

    suspend fun getBooksData(): Resource<List<BookResponse>> {
        val result = bookRepository.getBookData()
        if (result is Resource.Success) {
            isLoading.value = true
            _getBookData.value = result.data!!
        }
        return result
    }

    suspend fun getBookDataFromId(bookId: Int): Resource<BookDetailResponse> {
        val result = bookRepository.getBookFromBookId(bookId)
        if (result is Resource.Success) {
            isLoadingBook.value = true
            _getBookDataFromId.value = result.data!!
        }
        return result
    }
}