package com.varsha.bookstore.component.bookdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varsha.bookstore.model.BookDetailResponse
import java.util.*

@Composable
fun BookDetailInnerItemScreen(bookDetailResponse: BookDetailResponse) {
    val currencySymbol =
        Currency.getInstance(bookDetailResponse.currencyCode).getSymbol(Locale.getDefault())
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {

            // Title
            Text(
                text = bookDetailResponse.title,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Author
            Text(
                text = bookDetailResponse.author,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.body1,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // ISBN
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                text = "ISBN - ${bookDetailResponse.isbn}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.body2,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Description
            Text(
                text = bookDetailResponse.description,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(4.dp)
                    .fillMaxWidth()
                    .weight(1f),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.body2,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Add to cart Button
            Button(
                shape = RoundedCornerShape(20.dp),
                onClick = { print(bookDetailResponse.title + "Book Clicked") },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Book Price: ${bookDetailResponse.price}$currencySymbol",
                    fontSize = 15.sp
                )
            }
        }
    }
}