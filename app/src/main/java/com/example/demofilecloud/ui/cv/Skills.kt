package com.example.demofilecloud.ui.cv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demofilecloud.ui.theme.Secondary
import com.google.accompanist.flowlayout.FlowRow

object Skills {

    @Composable
    internal fun Skills(vararg skill: String) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Texts.Header(text = "Skills")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 15.dp
            ) {
                skill.forEach {
                    SingleSkillCard(skill = it)
                }
            }
        }

    }

    @Composable
    private fun SingleSkillCard(skill: String) {
        Surface(
            elevation = 6.dp,
            shape = RoundedCornerShape(50),
            color = Secondary
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(30.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 7.dp, bottom = 8.dp)
            ) {
                Text(
                    text = skill,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }

}