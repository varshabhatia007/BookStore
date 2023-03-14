package com.varsha.bookstore.component.booklist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.varsha.bookstore.R
import com.varsha.bookstore.component.common.CircularProgressComponent
import com.varsha.bookstore.component.common.ErrorComponent
import com.varsha.bookstore.model.BookResponse
import com.varsha.bookstore.utility.Resource
import com.varsha.bookstore.viewmodel.BookStoreViewModel

@Composable
fun HomeScreen(
    showDetailScreen: (Int) -> Unit = {},
    viewModel: BookStoreViewModel = hiltViewModel()
) {
    val bookInfo =
        produceState<Resource<List<BookResponse>>>(initialValue = Resource.Loading())
        {
            value = viewModel.getBooksData()
        }.value

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
        when (bookInfo) {
            is Resource.Loading -> {
                CircularProgressComponent()
            }
            is Resource.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(bookInfo.data!!.size) { index ->
                        BookInnerItemsScreen(
                            bookInfo.data[index],
                            itemOnClick = { showDetailScreen(it) })
                    }
                }
            }
            is Resource.Error -> {
                ErrorComponent(bookInfo.message.toString())
            }
        }
    }
}