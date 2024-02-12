package com.example.supertipapp

import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supertipapp.ui.theme.SuperTipAppTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperTipAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipApp()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TipApp() {

    var tipInput by remember { mutableStateOf("") }
    var amountInput by remember {  mutableStateOf("")}
    var isRoundInput by remember { mutableStateOf(false) }

    var amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipPerctange = tipInput.toDoubleOrNull()?: 0.0
    var tip = calculateTip(amount=amount, tipPerctange=tipPerctange, isRoundInput)






    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .statusBarsPadding()
            .padding(all=40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
    ) {
        Text(text = stringResource(R.string.calculate_tip),
            modifier=Modifier.align(alignment=Alignment.Start)
            )
        Spacer(modifier=Modifier.height(20.dp))
        NumberEditField(
            value = amountInput,
            onValueChange = {amountInput = it},
            label = R.string.tip_amount,
            leadingIcon = R.drawable.percent,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth(),

            )
        Spacer(modifier=Modifier.height(20.dp))


        NumberEditField(
            value = tipInput,
            onValueChange = { tipInput = it},
            label = R.string.bill_amount ,
            leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType=KeyboardType.Number,
                imeAction = ImeAction.Done

            ),
            )
        Spacer(modifier=Modifier.height(20.dp))


        Row(
            modifier=Modifier.fillMaxWidth()
        ) {
            Text(
                text="Round up tip?",
                modifier=Modifier

            )
            Switch(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                checked = isRoundInput, onCheckedChange = {isRoundInput =  it})
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text="Tip Amount: $tip",
            style = MaterialTheme.typography.displayMedium,
            modifier=Modifier.align(Alignment.Start)


            )


    }

}

@Composable
fun NumberEditField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions


) {
    
    TextField(
        value=value,
        onValueChange=onValueChange,
        label={Text(text= stringResource(id = label))},
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null)},
        singleLine = true,
        keyboardOptions=keyboardOptions,
        modifier=Modifier.fillMaxWidth()
    )
}


fun calculateTip(amount: Double,  tipPerctange: Double=15.0,  isRound: Boolean=false): String  {

    if(amount <= 0.0 ) {
        return NumberFormat.getCurrencyInstance().format(0.0)

    }

    var tipAmount = amount * tipPerctange / 100

    if(isRound) {
        tipAmount =  ceil(tipAmount)
    }

    return NumberFormat.getCurrencyInstance().format(tipAmount)



}











