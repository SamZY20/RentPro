package com.example.rentpro.presentation.tenants.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentpro.R
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import com.example.rentpro.presentation.widgets.MyOutlinedTextField
import com.example.rentpro.utils.Resource

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(viewModel: AuthViewModel?, navController: NavController)
{
    val getUser by viewModel!!.user.collectAsState()

    LaunchedEffect(Unit){
        viewModel?.getUser()
    }
    Log.i("USER", getUser?.contactNum.toString())
    val localFocusManager = LocalFocusManager.current
    // State
    var fullName by remember { mutableStateOf(getUser?.fullName ?:"") }
    var contactNum by remember {  mutableStateOf(getUser?.contactNum ?:"") }
    var identificationNum by remember { mutableStateOf(getUser?.identificationNum ?:"") }
    var password by remember { mutableStateOf("") }

    // Update flow
    val updateFlow = viewModel?.updateFlow?.collectAsState()

    LaunchedEffect(getUser) {
        fullName = getUser?.fullName ?: ""
        contactNum = getUser?.contactNum ?:""
        identificationNum = getUser?.identificationNum ?:""
    }

    updateFlow?.value?.let {
        when(it){
            is Resource.Error -> {
                val context = LocalContext.current
                LaunchedEffect(updateFlow?.value){
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
            is Resource.Loading -> {}
            is Resource.Success ->
            {
                val context = LocalContext.current
                LaunchedEffect(updateFlow?.value){
                    Toast.makeText(context, "Your profile has been updated!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Surface(color = Color.White) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }) {
            item {
                // Top Section with Blue Background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.secondary)
                ){
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                            .clickable { navController.popBackStack() }
                    ) {
                        Surface(
                            color = Color.White,
                            shape = CircleShape,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .size(16.dp)
                            )
                        }
                    }
                }
            }

            item{
                // Profile Image
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .offset(y = -100.dp),
                     horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(170.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape)
                    )
                }
            }
            item{
                // Profile Details Section
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .offset(y = -90.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = fullName,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 26.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Tenant",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray
                        )

                        Column(modifier = Modifier.padding(horizontal = 10.dp))
                        {
                            MyOutlinedTextField(
                                value = fullName,
                                onValueChange = { fullName = it },
                                label = "Full Name",
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next,
                                ),
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            MyOutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = "Update Password",
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Next
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            MyOutlinedTextField(
                                value = contactNum,
                                onValueChange = { contactNum = it },
                                label = "Contact Number",
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Phone,
                                    imeAction = ImeAction.Next
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            MyOutlinedTextField(
                                value = identificationNum,
                                onValueChange = { identificationNum = it },
                                label = "Identification Num",
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(onClick = { viewModel?.update(fullName!!, contactNum, identificationNum, password) }, shape = CircleShape, modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp))
                            {
                                if(updateFlow?.value is Resource.Loading)
                                {
                                    androidx.compose.material.CircularProgressIndicator(
                                        modifier = Modifier.size(
                                            24.dp
                                        ), color = Color.White
                                    )
                                } else {
                                    Text(text = "Update", modifier = Modifier.padding(horizontal = 32.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
