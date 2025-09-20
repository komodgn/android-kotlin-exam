package com.example.noti.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noti.home.domain.Product
import com.example.noti.core.presentation.designsystem.theme.NotiTheme

@Composable
fun ProductListScreen() {
    val products = productList

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    NotiTheme {
        ProductListScreen()
    }
}

// 목 데이터
val productList = listOf<Product>(
    Product(1, R.drawable.shoes, "블랙 슈즈", 150000),
    Product(2, R.drawable.cap, "블랙 캡", 55000),
    Product(1, R.drawable.shoes, "블랙 슈즈", 150000),
    Product(2, R.drawable.cap, "블랙 캡", 55000),
    Product(5, R.drawable.top, "베이지 셔츠", 45000),
    Product(1, R.drawable.shoes, "블랙 슈즈", 150000),
    Product(5, R.drawable.top, "베이지 셔츠", 45000),
    Product(2, R.drawable.cap, "블랙 캡", 55000),
    Product(5, R.drawable.top, "베이지 셔츠", 45000),
)