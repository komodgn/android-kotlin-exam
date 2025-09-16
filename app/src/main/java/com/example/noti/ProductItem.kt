package com.example.noti

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noti.ui.theme.NotiTheme

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
//                    .clip(shape = RoundedCornerShape(12.dp))
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = product.name,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
            Text(
                text = "${product.price}원",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    NotiTheme {
        ProductItem(
            product = Product(
                id = 1,
                imageUrl = R.drawable.shoes,
                name = "블랙 슈즈",
                price = 350000
            )
        )
    }
}
