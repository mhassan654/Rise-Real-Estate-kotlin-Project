package com.saavatech.riserealestate.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.components.ButtonTextComponent
import com.saavatech.riserealestate.presentation.home.rowButton
import com.saavatech.riserealestate.ui.theme.TextColorOne
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AppBottomSheet(
    showBottomSheet: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    scope: CoroutineScope,
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            shape =
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp,
                ),
            //                scrimColor = MaterialTheme.colorScheme.primary,
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(12.dp)
                        .clickable {
                            scope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        !showBottomSheet
                                    }
                                }
                        },
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        color = TextColorOne,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        text = "Select Location",
                    )

                    TextButton(
                        colors =
                            ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.primary,
                            ),
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = "Edit",
                        )
//
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    rowButton(
                        location = "Old Kampala primary school , sir appolo road",
                        bgColor = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    rowButton(
                        location = "Old Kampala primary school , sir appolo road",
                        bgColor = Color.White,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    ButtonTextComponent(value = "Choose Location", width = 300.dp, clickAction = {})
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
