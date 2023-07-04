package com.example.rentpro.presentation.tenants.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.rentpro.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Highlights()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.highlights_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.highlight_description),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            HighlightCard(
                imageRes = R.drawable.img_placeholder,
                title = stringResource(id = R.string.highlight_title_1),
                description = stringResource(id = R.string.highlight_description_1)
            )
            HighlightCard(
                imageRes = R.drawable.img_placeholder,
                title = stringResource(id = R.string.highlight_title_2),
                description = stringResource(id = R.string.highlight_description_2)
            )
            HighlightCard(
                imageRes = R.drawable.img_placeholder,
                title = stringResource(id = R.string.highlight_title_3),
                description = stringResource(id = R.string.highlight_description_3)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighlightCard(imageRes: Int, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Highlight Image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(start = 8.dp))
            {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color.Gray,
                )
            }
        }
    }
}