package com.varsha.bookstore.mapper

import com.varsha.bookstore.data.BookResponseModel
import com.varsha.bookstore.model.BookResponse
import com.varsha.bookstore.utility.ApiMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookInfoMapper @Inject constructor() : ApiMapper<BookResponse, BookResponseModel> {
    override fun mapToDomain(apiResponse: BookResponse): BookResponseModel = BookResponseModel(
        id = apiResponse.id,
        title = apiResponse.title,
        price = apiResponse.price,
        currencyCode = apiResponse.currencyCode,
        author = apiResponse.author,
    )
}
