package com.example.demofilecloud.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.demofilecloud.R

val interFont = FontFamily(
    Font(R.font.inter_light, weight = FontWeight.Light), // 300
    Font(R.font.inter_regular, weight = FontWeight.Normal), // 400
    Font(R.font.inter_medium, weight = FontWeight.Medium), // 500
    Font(R.font.inter_semibold, weight = FontWeight.SemiBold), // 600
    Font(R.font.inter_bold, weight = FontWeight.Bold), // 700
)

val Typography = Typography(
    defaultFontFamily = interFont
)