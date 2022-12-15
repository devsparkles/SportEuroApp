package com.devsparle.sporteuroapp.presentation.screens.feed_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devsparle.sporteuroapp.ui.theme.DarkBlue

@Composable
fun TagChip(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .background(color = DarkBlue, shape = RoundedCornerShape(6.dp))
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            text = text.uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}