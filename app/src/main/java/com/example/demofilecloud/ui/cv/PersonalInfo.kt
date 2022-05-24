package com.example.demofilecloud.ui.cv

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demofilecloud.ui.theme.Secondary

object PersonalInfo {

    @Composable
    internal fun SelfieSectionContent(@DrawableRes drawableRes: Int, text: String) {
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = "face",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
        )
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    @Composable
    internal fun PersonalInfoDivider() {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.01f)
                .height(76.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .height(50.dp)
                    .background(color = Secondary)
            )
        }
    }

    @Composable
    internal fun PersonalInfoRow(@DrawableRes drawableRes: Int, text: String) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = drawableRes),
                contentDescription = "back",
                modifier = Modifier
                    .padding(5.dp)
                    .size(16.dp)
            )
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }

}