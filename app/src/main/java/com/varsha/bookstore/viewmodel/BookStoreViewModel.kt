package com.varsha.bookstore.viewmodel

import androidx.lifecycle.ViewModel
import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.model.BookResponse
import com.varsha.bookstore.repository.BookRepository
import com.varsha.bookstore.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BookStoreViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private var _getBookData = MutableStateFlow<Resource<List<BookResponse>>>(Resource.Loading())
    val getBookData get() = _getBookData

    private var _getBookDataFromId =
        MutableStateFlow<Resource<BookDetailResponse>>(Resource.Loading())
    val getBookDataFromId get() = _getBookDataFromId

    suspend fun getBooksData(): Resource<List<BookResponse>> {
        val result = bookRepository.getBookData()
        _getBookData.value = Resource.Loading()
        when (result) {
            is Resource.Success -> {
                _getBookData.value = Resource.Success(result.data!!)
            }
            is Resource.Error -> {
                _getBookData.value = Resource.Error(result.message.toString(), null)
            }
        }
        return result
    }

    suspend fun getBookDataFromId(bookId: Int): Resource<BookDetailResponse> {
        val result = bookRepository.getBookFromBookId(bookId)
        _getBookDataFromId.value = Resource.Loading()
        if (result is Resource.Success) {
            _getBookDataFromId.value = Resource.Success(result.data!!)
        } else if (result is Resource.Error) {
            _getBookDataFromId.value = Resource.Error(result.message.toString())
        }
        return result
    }
}