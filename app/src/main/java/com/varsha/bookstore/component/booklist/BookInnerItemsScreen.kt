package com.varsha.bookstore.component.booklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varsha.bookstore.model.BookResponse
import java.util.*

@Composable
fun BookInnerItemsScreen(bookResponse: BookResponse, itemOnClick: (Int) -> Unit = {}) {
    val currencySymbol = Currency.getInstance(bookResponse.currencyCode).getSymbol(Locale.getDefault())

    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .clickable { itemOnClick(bookResponse.id) }
            .fillMaxWidth()
            .padding(5.dp)
            .height(120.dp),
        elevation = 10.dp,
        shape = CutCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = "Title: ${bookResponse.title}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Author
            Text(
                text = "Author: ${bookResponse.author}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Book price with currency symbol
            Text(
                text = "Price: ${bookResponse.price} $currencySymbol",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}