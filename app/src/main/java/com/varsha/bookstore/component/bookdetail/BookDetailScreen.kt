package com.varsha.bookstore.component.bookdetail

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.varsha.bookstore.R
import com.varsha.bookstore.component.common.CircularProgressComponent
import com.varsha.bookstore.component.common.ErrorComponent
import com.varsha.bookstore.model.BookDetailResponse
import com.varsha.bookstore.utility.Resource
import com.varsha.bookstore.viewmodel.BookStoreViewModel

@Composable
fun BookDetailScreen(
    navigateUp: () -> Unit = {},
    bookId: Int,
    viewModel: BookStoreViewModel = hiltViewModel()
) {
    val bookDetailInfo =
        produceState<Resource<BookDetailResponse>>(initialValue = Resource.Loading()) {
            value = viewModel.getBookDataFromId(bookId)
        }.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.book_details)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        },
        content = {
            when (bookDetailInfo) {
                is Resource.Loading -> {
                    CircularProgressComponent()
                }
                is Resource.Success -> {
                    if (bookDetailInfo.data!!.title.isNotEmpty()) {
                        BookDetailInnerItemScreen(bookDetailInfo.data)
                    }
                }
                is Resource.Error -> {
                    ErrorComponent(errorText = bookDetailInfo.message.toString())
                }
            }
        }
    )
}


