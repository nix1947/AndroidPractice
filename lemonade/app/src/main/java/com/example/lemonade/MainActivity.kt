package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }


    Scaffold(
        // top bar
        topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Lemonade", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
    }) { innerPadding ->
        Surface(
            modifier= Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (currentStep) {

                1 -> ImageAndText(
                    textResourceId = R.string.step1,
                    imageResourceId = R.drawable.lemon_tree,
                    imageContentDescriptionResourceId = R.string.lemon_tree,
                    treeImageClickHandler = {
                        currentStep = 2
                        squeezeCount = (1..4).random()
                    },
                    modifier = Modifier
                )

                2 -> ImageAndText(
                    textResourceId = R.string.step2,
                    imageResourceId = R.drawable.lemon_squeeze,
                    imageContentDescriptionResourceId = R.string.lemon,
                    treeImageClickHandler = {
                        squeezeCount--
                        if (squeezeCount == 0) currentStep = 3
                    },
                )


                3 -> ImageAndText(
                    textResourceId = R.string.step3,
                    imageResourceId = R.drawable.lemon_drink,
                    imageContentDescriptionResourceId = R.string.lemon_glass,
                    treeImageClickHandler = { currentStep = 4 })

                4 -> ImageAndText(
                    textResourceId = R.string.step4,
                    imageResourceId = R.drawable.lemon_restart,
                    imageContentDescriptionResourceId = R.string.empty_glass,
                    treeImageClickHandler = { currentStep = 1 })
            }
        }




    }


}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}

@Composable
fun ImageAndText(
    textResourceId: Int,
    imageResourceId: Int,
    imageContentDescriptionResourceId: Int,
    treeImageClickHandler: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = treeImageClickHandler,

            ) {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(id = imageContentDescriptionResourceId),
                modifier = Modifier
                    .padding(20.dp)
                    .scale(1.4f),
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Text
        Text(
            text = stringResource(id = textResourceId),
            fontWeight = FontWeight.Bold,

            )

    }

}