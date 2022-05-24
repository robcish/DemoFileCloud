package com.example.demofilecloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demofilecloud.ui.cv.PersonalInfo
import com.example.demofilecloud.ui.cv.PersonalInfo.PersonalInfoDivider
import com.example.demofilecloud.ui.cv.PersonalInfo.PersonalInfoRow
import com.example.demofilecloud.ui.cv.Skills.Skills
import com.example.demofilecloud.ui.cv.Texts.Biography
import com.example.demofilecloud.ui.cv.TopBar.TopBar
import com.example.demofilecloud.ui.theme.DemoFileCloudTheme

class CVActivity : ComponentActivity() {

    private val back = { onBackPressed() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DemoFileCloudTheme {
                Scaffold(
                    topBar = {
                        TopBar("Robert - Virtual CV", back)
                    }
                ) {
                    CV_Content()
                }
            }
        }
    }
}

@Composable
fun CV_Content() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            PersonalContent()

            Skills(
                "Android", "Kotlin", "JAVA", "git", "Compose",
                "Android Studio", "RX", "Databinding", "other"
            )

            Biography(text = stringResource(id = R.string.biography_robert))
        }
    }
}

@Composable
private fun PersonalContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.49f)
                .height(76.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PersonalInfo.SelfieSectionContent(drawableRes = R.drawable.selfie, text = "Robert R.")
        }
        PersonalInfoDivider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .padding(start = 15.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            PersonalInfoRow(drawableRes = R.drawable.ic_at, text = "robr1993@gmail.com")
            PersonalInfoRow(drawableRes = R.drawable.ic_phone, text = "+48 515-522-428")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoFileCloudTheme {
        Scaffold(
            topBar = {
                TopBar("Robert - Virtual CV") {}
            }
        ) {
            CV_Content()
        }

    }
}