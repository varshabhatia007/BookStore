package com.varsha.bookstore.mapper

import com.varsha.bookstore.data.BookDetailResponseModel
import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.utility.ApiMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookInfoDetailMapper @Inject constructor() :
    ApiMapper<BookDetailResponse, BookDetailResponseModel> {
    override fun mapToDomain(apiResponse: BookDetailResponse): BookDetailResponseModel =
        BookDetailResponseModel(
            id = apiResponse.id,
            title = apiResponse.title,
            description = apiResponse.description,
            price = apiResponse.price,
            currencyCode = apiResponse.currencyCode,
            author = apiResponse.author,
            isbn = apiResponse.isbn
        )
}


