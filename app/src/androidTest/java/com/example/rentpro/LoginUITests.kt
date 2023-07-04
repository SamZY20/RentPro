/*
package com.example.rentpro

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.rentpro.presentation.auth.screens.LoginScreen
import com.example.rentpro.ui.theme.RentProTheme
import org.junit.Rule
import org.junit.Test

class LoginUITests
{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_with_correct_credentials() {
        // Set up the test
        composeTestRule.setContent {
            RentProTheme {
                LoginScreen { email, password ->
                    assert(email == "testacc@mail.com" && password == "password123")
                }
            }
        }

        // Enter email and password
        composeTestRule.onNodeWithTag("EmailTextField")
            .performTextInput("testacc@mail.com")
        composeTestRule.onNodeWithTag("PasswordTextField")
            .performTextInput("password123")

        // Click the login button
        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()

        // Verify the success message is displayed
        composeTestRule.onNodeWithText("Login successful")
            .assertIsDisplayed()
    }

    @Test
    fun login_with_incorrect_credentials() {
        // Set up the test
        composeTestRule.setContent {
            RentProTheme {
                LoginScreen { email, password ->
                    assert(email != "testacc@mail.com" || password != "password123")
                }
            }
        }

        // Enter incorrect email and password
        composeTestRule.onNodeWithTag("EmailTextField")
            .performTextInput("test@gmail.com")
        composeTestRule.onNodeWithTag("PasswordTextField")
            .performTextInput("admin321")

        // Click the login button
        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()

        // Verify the error message is displayed
        composeTestRule.onNodeWithText("Wrong credentials")
            .assertIsDisplayed()
    }
}*/
