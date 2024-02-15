package com.example.coursegrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursegrid.data.DataSource
import com.example.coursegrid.model.Topic
import com.example.coursegrid.ui.theme.CourseGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseApp()
                }
            }
        }
    }
}


@Composable
fun CourseApp() {
    GridView(topicList = DataSource.getAllData() )

}


@Composable
fun CourseCard(modifier: Modifier, topicTitleResourceId: Int, topicImageResourceId: Int, viewCount: Int ) {
    Card(
        modifier= modifier
            .height(68.dp)
            .width(200.dp)
    ) {
        Row(modifier=Modifier) {
            // image Box
            Box(modifier=Modifier) {
                Image(
                    painter = painterResource(topicImageResourceId),
                    contentDescription = stringResource(topicTitleResourceId),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(68.dp).aspectRatio(1f)
                )
            }

            // Content Box
            Column(
                modifier=Modifier.padding(start=16.dp, top=16.dp, end=16.dp, bottom = 0.dp)
            ) {
               Text(
                   text = stringResource(topicTitleResourceId),
                   style=MaterialTheme.typography.bodyMedium,
                   modifier=Modifier.padding(bottom=8.dp)
               )


                Row(modifier=Modifier, horizontalArrangement =  Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                   Image(
                       painter = painterResource(id = R.drawable.ic_grain),
                       contentDescription = null,
                       colorFilter = ColorFilter.tint(Color.DarkGray)                   )

                    Spacer(modifier=Modifier.width(8.dp))
                    Text(text= viewCount.toString(),
                        style=MaterialTheme.typography.labelMedium
                        )

                }
            }
        }

    }
}


@Composable
fun GridView(topicList: List<Topic>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id=R.dimen.padding_small)),
        modifier=Modifier.padding(dimensionResource(id = R.dimen.padding_small))
    ) {
      items(topicList) {
          topic ->
          CourseCard(modifier=Modifier, topic.topicTitleResource, topic.topicImageResourceId, topic.viewCount)
      }
    }

}
