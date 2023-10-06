package com.varsha.bookstore.component.booklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.varsha.bookstore.R
import com.varsha.bookstore.component.common.CircularProgressComponent
import com.varsha.bookstore.component.common.ErrorComponent
import com.varsha.bookstore.data.BookResponseModel
import com.varsha.bookstore.utility.Resource
import com.varsha.bookstore.viewmodel.BookStoreViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    showDetailScreen: (Int) -> Unit = {},
    viewModel: BookStoreViewModel = hiltViewModel(),
) {
    val bookInfo =
        produceState<Resource<List<BookResponseModel>>>(initialValue = Resource.Loading()) {
            value = viewModel.getBooksData()
        }.value

    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            stringResource(R.string.book_app_name),
                        )
                    }
                },
            )
        },
        content = { padding ->
            when (bookInfo) {
                is Resource.Loading -> {
                    CircularProgressComponent()
                }

                is Resource.Success -> {
                    LazyColumn(
                        modifier = Modifier.consumeWindowInsets(padding),
                        contentPadding = padding,
                    ) {
                        items(bookInfo.data!!.size) { index ->
                            BookInnerItemsScreen(
                                bookInfo.data[index],
                                itemOnClick = { showDetailScreen(it) },
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    ErrorComponent(bookInfo.message.toString())
                }
            }
        },
        modifier = Modifier.padding(10.dp),
    )
}
