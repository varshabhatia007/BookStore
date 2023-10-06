package com.varsha.bookstore.component.bookdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varsha.bookstore.data.BookDetailResponseModel
import java.text.NumberFormat
import java.util.*

@Composable
fun BookDetailInnerItemScreen(bookDetailResponseModel: BookDetailResponseModel) {
    val currencyAmount =
        NumberFormat.getNumberInstance(Locale.getDefault()).format(bookDetailResponseModel.price)
    val currencySymbol =
        Currency.getInstance(bookDetailResponseModel.currencyCode).getSymbol(Locale.getDefault())
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
        ) {
            // Title
            Text(
                text = bookDetailResponseModel.title,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Author
            Text(
                text = bookDetailResponseModel.author,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 15.sp,
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // ISBN
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                text = "ISBN - ${bookDetailResponseModel.isbn}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 15.sp,
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Description
            Text(
                text = bookDetailResponseModel.description,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(4.dp)
                    .fillMaxWidth()
                    .weight(1f),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 15.sp,
            )
            Spacer(modifier = Modifier.padding(5.dp))

            // Add to cart Button
            Button(
                shape = RoundedCornerShape(20.dp),
                onClick = { print(bookDetailResponseModel.title + "Book Clicked") },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Book Price: $currencyAmount$currencySymbol",
                    fontSize = 15.sp,
                )
            }
        }
    }
}
