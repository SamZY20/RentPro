package com.example.rentpro.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rentpro.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MySearchBar(
    searchKeyword: String,
    onSearchKeywordChange: (String)->Unit
)
{
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        .background(Color(0xFFECECEC).copy(alpha = 0.5f))){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier.padding(horizontal = 4.dp)
                    .size(35.dp)
            )
            TextField(
                value = searchKeyword,
                onValueChange = { keyword->onSearchKeywordChange(keyword) },
                placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    placeholderColor = Color.Gray,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier.weight(1f),
            )
        }
    }
}