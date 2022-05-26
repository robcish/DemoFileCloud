package com.example.demofilecloud.ui.cv

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demofilecloud.R
import com.example.demofilecloud.ui.theme.DemoFileCloudTheme
import com.example.demofilecloud.ui.theme.Secondary

object Slider {

    class SliderItem(@DrawableRes val icon: Int)

    @Composable
    internal fun Slider() {

        val list = listOf<SliderItem>(
            SliderItem(icon = R.drawable.ic_engineer),
            SliderItem(icon = R.drawable.ic_astro),
            SliderItem(icon = R.drawable.ic_king),
            SliderItem(icon = R.drawable.ic_ninja)
        )

        val longList = list+list+list+list

        Column(modifier = Modifier.fillMaxWidth()) {
            Texts.Header(text = "Gallery")
            LazyRow(
                content = {
                    items(longList.size) { index ->
                        if (index == 0) {
                            Spacer(modifier = Modifier.width(16.dp))
                        }

                        Surface(
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(1.dp, Secondary),
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clip(RoundedCornerShape(5.dp))
                                .size(width = 95.dp, height = 93.dp)
                                .shadow(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = longList[index].icon),
                                    contentDescription = "icon",
                                    tint = Secondary,
                                    modifier = Modifier
                                        .size(width = 43.dp, height = 47.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoFileCloudTheme {
        Surface {
            Slider.Slider()
        }
    }
}