package com.example.demofilecloud.ui.cv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Texts {

    @Composable
    internal fun Header(text: String) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 15.dp, end = 20.dp)
        )
    }

    @Composable
    internal fun Body(text: String) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 15.dp, end = 20.dp)
        )
    }

    @Composable
    internal fun Biography(text: String) {
        Column() {
            Header(text = "Bio")
            Body(text = text)
        }
    }
}