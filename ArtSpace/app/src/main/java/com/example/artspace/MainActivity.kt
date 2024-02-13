package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .statusBarsPadding()
                        .safeDrawingPadding()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    var titles = listOf(
        Pair("Title of art", "By Manoj (2024)"),
        Pair("Another Title", "By John (2023)"),
        Pair("Yet Another Title", "By Jane (2022)")
    )
    var currentImage by remember { mutableStateOf(0) }
    var currentTitleIndex by remember { mutableStateOf(0) }

    // Parent Column to align all element
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp)
    ) {

        Box(modifier=Modifier.padding(20.dp).background(color=Color.LightGray, shape = RoundedCornerShape(5.dp))) {
            Column(
                modifier = Modifier.height(400.dp).padding(20.dp).shadow(elevation = 20.dp)
            ) {

                when (currentImage) {

                    0 -> Image(
                        painter = painterResource(id = R.drawable.art_1), contentDescription = null,
                        modifier = Modifier,
                        contentScale = ContentScale.Crop

                    )

                    1 -> Image(
                        painter = painterResource(id = R.drawable.art_2), contentDescription = null,
                        modifier = Modifier,
                        contentScale = ContentScale.Crop

                    )

                    2 -> Image(
                        painter = painterResource(id = R.drawable.art_3), contentDescription = null,
                        modifier = Modifier,
                        contentScale = ContentScale.Crop

                    )
                }


            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Title artist Year
        Row(
            modifier = Modifier.background(color=Color.LightGray, shape = RoundedCornerShape(16.dp)).fillMaxWidth().height(80.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = titles[currentTitleIndex].first.uppercase(),
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = titles[currentTitleIndex].second.uppercase(),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))

        // Buttons
        Row(
            modifier = Modifier
        ) {
            Button(onClick = {
                if (currentImage == 0) {
                    currentImage = 2
                    currentTitleIndex = 2

                } else {
                    currentImage -= 1
                    currentTitleIndex -= 1

                }

            }) {
                Text(text = "Previous Button")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = {
                if (currentImage == 2) {
                    currentImage = 0
                    currentTitleIndex =0

                }
                else {
                    currentImage += 1
                    currentTitleIndex += 1

                }

            }) {
                Text(text = "Next Button")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}