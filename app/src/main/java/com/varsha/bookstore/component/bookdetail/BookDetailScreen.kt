package com.varsha.bookstore.component.bookdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.varsha.bookstore.utility.Resource
import com.varsha.bookstore.viewmodel.BookStoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BookDetailScreen(
    navigateUp: () -> Unit = {},
    viewModel: BookStoreViewModel,
    bookId: Int
) {
    val scope = rememberCoroutineScope()
    BookDetailScreen(scope, viewModel, navigateUp, bookId)
}

/**
 * Book detail screen view stateless
 */
@Composable
fun BookDetailScreen(
    scope: CoroutineScope,
    viewModel: BookStoreViewModel,
    navigateUp: () -> Unit = {},
    bookId: Int
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Details") },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            val getBookDataFromId = viewModel.getBookDataFromId.observeAsState()
            scope.launch {
                val result = viewModel.getBookDataFromId(bookId = bookId)
                if (result is Resource.Success) {
                    print("Success")
                } else if (result is Resource.Error) {
                    print("Error")
                }
            }
            if (!viewModel.isLoadingBook.value) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            if (viewModel.isLoadingBook.value) {
                if (getBookDataFromId.value!!.title.isNotEmpty()) {
                    BookDetailInnerItemScreen(viewModel.getBookDataFromId.value!!)
                }
            }
        }
    )
}


