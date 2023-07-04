package com.example.rentpro.presentation.auth.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentpro.R
import com.example.rentpro.navigation.NavRoutes
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import com.example.rentpro.presentation.widgets.MyOutlinedTextField
import com.example.rentpro.ui.theme.RentProTheme
import com.example.rentpro.utils.Resource

@Composable
fun LoginScreen (viewModel: AuthViewModel?, navController: NavController){
    val localFocusManager = LocalFocusManager.current
    // State
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}

    val loginFlow = viewModel?.loginFlow?.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        contentAlignment = Alignment.Center
    )
    {
        Column()
        {
            Image(
                painter = painterResource(R.drawable.rentpro_logo),
                contentDescription = "RentPro Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email Address",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

                MyOutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { viewModel?.login(email, password) }, shape = CircleShape, modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp))
                {
                    if(loginFlow?.value is Resource.Loading)
                    {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                    } else {
                        Text(text = "Login", modifier = Modifier.padding(horizontal = 32.dp))
                    }
                }

                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account? ")
                        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                            append("Sign Up")
                        }
                    },
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoutes.Registration.route){
                            popUpTo(NavRoutes.Login.route) {inclusive = true}
                        }
                    }
                )
            }
        }
    }
    loginFlow?.value?.let {
        when(it){
            is Resource.Error -> {
                val context = LocalContext.current
                LaunchedEffect(loginFlow?.value){
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
            is Resource.Loading->{}
            is Resource.Success -> {
                LaunchedEffect(Unit){
                    navController.navigate(NavRoutes.Home.route){
                        popUpTo(NavRoutes.Login.route) {inclusive = true}
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    RentProTheme {
        LoginScreen(null, rememberNavController())
    }
}

