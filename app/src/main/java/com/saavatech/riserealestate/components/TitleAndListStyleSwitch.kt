package com.saavatech.riserealestate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne

@Composable
fun TitleAndListStyleSwitch(
    titleCategory: String,
    counter: Int,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text =
                buildAnnotatedString {
                    withStyle(
                        style =
                            SpanStyle(
                                color = TextColorOne,
                                fontSize = 25.sp,
                            ),
                    ) {
                        append("Found")
                    }

//                    properties counter
                    withStyle(
                        style =
                            SpanStyle(
                                color = TextColorBold,
                                fontWeight = FontWeight(700),
                                fontSize = 22.sp,
                            ),
                    ) {
                        append(counter.toString())
                    }

                    append(" ")

                    // category e.g estates
                    withStyle(
                        style =
                            SpanStyle(
                                color = TextColorBold,
                                fontWeight = FontWeight(400),
                                fontSize = 22.sp,
                            ),
                    ) {
                        append(titleCategory)
                    }
                },
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Start,
            color = TextColorBold,
        )

        content()
    }
}
