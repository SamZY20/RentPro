package com.example.rentpro.presentation.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rentpro.R

@Composable
fun MyRatingScale(
    rating: Int,
    starSize: Dp = 24.dp,
    starSpacing: Dp = 4.dp,
    filledStarColor: Color = Color(0xFFFFC107),
    nonFilledStarColor: Color = Color.Gray
) {
    Row(modifier = Modifier.padding(start = starSpacing)) {
        repeat(5) { index ->
            val drawableRes = if (index < rating) {
                R.drawable.star_filled
            } else {
                R.drawable.star_empty
            }
            val tint = if (index < rating) {
                filledStarColor
            } else {
                nonFilledStarColor
            }

            IconButton(
                onClick = { /* Handle star click if needed */ },
                modifier = Modifier.width(starSize)
            ) {
                Icon(
                    painter = painterResource(id = drawableRes),
                    contentDescription = null,
                    tint = tint
                )
            }
        }
    }
}