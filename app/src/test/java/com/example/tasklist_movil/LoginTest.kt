package com.example.tasklist_movil

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.auth.FirebaseAuth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginTest {

    private lateinit var loginActivity: Login
    private lateinit var mockFirebaseAuth: FirebaseAuth

    @Before
    fun setup() {
        // Mock FirebaseAuth instance
        mockFirebaseAuth = mockk(relaxed = true)
        every { FirebaseAuth.getInstance() } returns mockFirebaseAuth

        // Create LoginActivity instance
        loginActivity = Login()
        loginActivity.firebaseAuth = mockFirebaseAuth
        loginActivity.binding = mockk(relaxed = true)
    }
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun TestCheck(){
        Assert.assertTrue(true)
    }

    @Test
    fun testSignInWithEmailAndPassword_Successful() {
        // Mock input values
        val email = "john@example.com"
        val pass = "password"

        // Mock FirebaseAuth signInWithEmailAndPassword
        every { mockFirebaseAuth.signInWithEmailAndPassword(email, pass) } returns mockk()

        // Call the method to be tested
        loginActivity.loginUser(email, pass)

        // Verify that the appropriate methods are called
        verify { mockFirebaseAuth.signInWithEmailAndPassword(email, pass) }
        verify { loginActivity.startActivity(any()) }
    }

    @Test
    fun testSignInWithEmailAndPassword_Failure() {
        // Mock input values
        val email = "john@example.com"
        val pass = "password"

        // Mock FirebaseAuth signInWithEmailAndPassword
        val mockException: Exception = mockk()
        every { mockFirebaseAuth.signInWithEmailAndPassword(email, pass) } throws mockException

        // Call the method to be tested
        loginActivity.loginUser(email, pass)

        // Verify that the appropriate Toast message is shown
        verify { loginActivity.showToast("Exception message") }
    }

    @Test
    fun testSignInWithEmailAndPassword_EmptyFields() {
        // Mock input values
        val email = ""
        val pass = ""

        // Call the method to be tested
        loginActivity.loginUser(email, pass)

        // Verify that the appropriate Toast message is shown
        verify { loginActivity.showToast("Fields cannot be empty") }
    }

    @Test
    fun testGoToRegister() {
        val mockIntent = mockk<Intent>(relaxed = true)
        every { anyConstructed<Intent>().apply { this@apply.flags = any() } } returns mockIntent

        loginActivity.goToRegister()

        verify { loginActivity.startActivity(mockIntent) }
    }

    @Test
    fun testGoToRestore() {
        val mockIntent = mockk<Intent>(relaxed = true)
        every { anyConstructed<Intent>().apply { this@apply.flags = any() } } returns mockIntent

        loginActivity.goTorestore()

        verify { loginActivity.startActivity(mockIntent) }
    }
}
