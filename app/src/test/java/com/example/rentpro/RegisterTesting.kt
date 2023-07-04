/*
package com.example.rentpro

import com.example.rentpro.domain.repository.AuthRepositoryImpl
import com.example.rentpro.utils.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RegisterTesting {
    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var firestore: FirebaseFirestore

    private lateinit var authRepository: AuthRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authRepository = AuthRepositoryImpl(firebaseAuth, firestore)
    }

    @Test
    fun registerAuthUser() = runBlocking {
        val fullName = "User Testing"
        val email = "testacc@mail.com"
        val password = "password123"
        val expectedUser: FirebaseUser? = mockUser() // Create a mock FirebaseUser object
        val expectedAuth = Resource.Success(expectedUser!!)

        // Create a mock Task<AuthResult> object
        val mockAuthResultTask: Task<AuthResult> = Mockito.mock(Task::class.java) as Task<AuthResult>

        Mockito.`when`(mockAuthResultTask.isSuccessful)
            .thenReturn(true)

        // Create a mock AuthResult object
        val authResult: AuthResult = Mockito.mock(AuthResult::class.java)

        // Stub the user property of the authResult
        Mockito.`when`(authResult.user).thenReturn(expectedUser)

        // Stub the result of the mockAuthResultTask using thenAnswer
        Mockito.`when`(mockAuthResultTask.result)
            .thenAnswer { invocation -> authResult }

        // Mock the signInWithEmailAndPassword function of FirebaseAuth
        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword(email, password))
            .thenReturn(mockAuthResultTask)

        val actualAuth = authRepository.registerInternal(fullName, email, password)

        Assert.assertEquals(expectedAuth, actualAuth)
    }

    // Helper function to create a mock AuthResult object
    private fun mockAuthResult(email: String, user: FirebaseUser?): AuthResult {
        val authResult: AuthResult = Mockito.mock(AuthResult::class.java)
        Mockito.`when`(authResult.user).thenReturn(user)
        Mockito.`when`(authResult.additionalUserInfo).thenReturn(null)
        Mockito.`when`(authResult.credential).thenReturn(null)
        return authResult
    }

    // Helper function to create a mock FirebaseUser object
    private fun mockUser(): FirebaseUser? {
        val mockFirebaseUser: FirebaseUser = Mockito.mock(FirebaseUser::class.java)
        return mockFirebaseUser
    }
}
*/
