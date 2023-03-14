package com.varsha.bookstore.component.bookdetail

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.varsha.bookstore.R
import com.varsha.bookstore.component.common.CircularProgressComponent
import com.varsha.bookstore.component.common.ErrorComponent
import com.varsha.bookstore.utility.Resource
import com.varsha.bookstore.viewmodel.BookStoreViewModel

@Composable
fun BookDetailScreen(
    viewModel: BookStoreViewModel,
    navigateUp: () -> Unit = {},
) {
    val getBookDataFromId = viewModel.getBookDataFromId.collectAsState().value
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
            when (getBookDataFromId) {
                is Resource.Loading -> {
                    CircularProgressComponent()
                }
                is Resource.Success -> {
                    if (getBookDataFromId.data!!.title.isNotEmpty()) {
                        BookDetailInnerItemScreen(getBookDataFromId.data)
                    }
                }
                is Resource.Error -> {
                    ErrorComponent(errorText = getBookDataFromId.message.toString())
                }
            }
        }
    )
}


