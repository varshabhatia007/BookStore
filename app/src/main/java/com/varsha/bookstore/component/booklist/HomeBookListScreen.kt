package com.varsha.bookstore.component.booklist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.primarySurface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.varsha.bookstore.R
import com.varsha.bookstore.utility.Resource
import com.varsha.bookstore.viewmodel.BookStoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: BookStoreViewModel,
    showDetailScreen: (Int) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    HomeScreen(
        scope = scope,
        viewModel = viewModel,
        itemOnClick = { showDetailScreen(it) },
    )
}

@Composable
private fun HomeScreen(
    itemOnClick: (Int) -> Unit = {},
    scope: CoroutineScope,
    viewModel: BookStoreViewModel
) {
    val getAllBooksData = viewModel.getBookData.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.primarySurface,
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            stringResource(R.string.book_app_name)
                        )
                    }

                },
            )
        }
    ) {
        scope.launch {
            val result = viewModel.getBooksData()
            if (result is Resource.Success) {
                print("Success")
            } else if (result is Resource.Error) {
                print("Error")
            }
        }

        if (!viewModel.isLoading.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        if (viewModel.isLoading.value) {
            if (viewModel.getBookData.value!!.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(getAllBooksData.value!!.size) { index ->
                        BookInnerItemsScreen(
                            getAllBooksData.value!![index],
                            itemOnClick = { itemOnClick(it) })
                    }
                }
            }
        }
    }
}