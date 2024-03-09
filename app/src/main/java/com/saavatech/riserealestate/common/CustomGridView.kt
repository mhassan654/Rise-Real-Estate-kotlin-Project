package com.saavatech.riserealestate.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GridView() {
    repeat(4) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(2) {
                VerticalPropertyCard()
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
