package com.example.supertipapp

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.supertipapp.ui.theme.SuperTipAppTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipAppUiTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        // Call the ui through composeTestRule
        composeTestRule.setContent {
            SuperTipAppTheme {
                TipApp()
            }
        }

        // set the value to Bill Amount field 10
        composeTestRule.onNodeWithText(text = "Tip Amount:").performTextInput("10")

        composeTestRule.onNodeWithText(text = "Bill Amount").performTextInput(text = "20")

        val expectedTip = calculateTip(amount = 10.0, tipPerctange = 20.0)

        composeTestRule.onNodeWithText(text = "Tip Amount: $expectedTip").assertExists()
    }


}