/*
package com.example.rentpro

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.rentpro.presentation.auth.screens.RegistrationScreen
import com.example.rentpro.ui.theme.RentProTheme
import org.junit.Rule
import org.junit.Test

class RegistrationUITests
{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<SignUpActivity>()


    @Test
    fun signUpFailed_InvalidEmailAndPassword() {

        composeTestRule.setContent {
            RentProTheme {
                RegistrationScreen { fullName, email, password, confpassword ->
                    assert(fullName != "admin" || email != "admin@gmail.com" || password != "admin123" || confpassword!= "admin123")
                }
            }
        }
        // Enter incorrect email and password
        composeTestRule.onNodeWithTag("fullNameTextField").performTextInput("Test User")
        composeTestRule.onNodeWithTag("EmailTextField").performTextInput("test@gmail.com")
        composeTestRule.onNodeWithTag("PasswordTextField").performTextInput("admin321")
        composeTestRule.onNodeWithTag("ConfirmPasswordTextField").performTextInput("admin321")

        // Click the login button
        composeTestRule.onNodeWithTag("signUpButton")
            .performClick()

        // Verify the error message is displayed
        composeTestRule.onNodeWithText("Wrong credentials")
            .assertIsDisplayed()

    }

    @Test
    fun signUpSuccess_ValidEmailAndPassword() {

        composeTestRule.setContent {
            RentProTheme {
                RegistrationScreen { fullName, email, password, confpassword ->
                    assert(fullName == "admin" && email == "admin@gmail.com" && password == "admin123" && confpassword == "admin123")
                }
            }
        }

        // Enter email and password
        composeTestRule.onNodeWithTag("fullNameTextField").performTextInput("admin")
        composeTestRule.onNodeWithTag("EmailTextField").performTextInput("admin@gmail.com")
        composeTestRule.onNodeWithTag("PasswordTextField").performTextInput("admin123")
        composeTestRule.onNodeWithTag("ConfirmPasswordTextField").performTextInput("admin123")

        // Click the login button
        composeTestRule.onNodeWithTag("signUpButton")
            .performClick()

        // Verify the success message is displayed
        composeTestRule.onNodeWithText("Sign Up successful")
            .assertIsDisplayed()

    }
}*/
