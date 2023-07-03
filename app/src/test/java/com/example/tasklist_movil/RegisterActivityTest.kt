package com.example.tasklist_movil

import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.ar.core.Config
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.mockk.MockKAnnotations
import io.mockk.MockKStubScope
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


internal class RegisterActivityTest{
    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    private lateinit var registerActivity: RegisterActivity
    private lateinit var mockFirebaseAuth: FirebaseAuth


    @Mock
    private lateinit var mockAuthResult: RegisterActivity

    @Before
    fun setup() {

        // Mock FirebaseAuth instance
        mockFirebaseAuth = mockk(relaxed = true)
        every { FirebaseAuth.getInstance() } returns mockFirebaseAuth

        // Create RegisterActivity instance
        registerActivity = RegisterActivity()
        registerActivity.firebaseAuth = mockFirebaseAuth
        registerActivity.binding = mockk(relaxed = true)
    }


    @Test
    fun TestCheck(){
        Assert.assertTrue(true)
    }

    @Test

    fun testCreateUserWithEmailAndPassword_Successful() {

        // Mock input values
        val name = "John"
        val email = "john@example.com"
        val pass = "password"
        val confirmPass = "password"

        // Mock FirebaseAuth createUserWithEmailAndPassword
        val mockAuthResult = mockk<RegisterActivity>()
        every { mockFirebaseAuth.createUserWithEmailAndPassword(email, pass) } returns mockk()


        // Call the method to be tested
        registerActivity.registerUser(name, email, pass, confirmPass)

        // Verify that the appropriate methods are called
        verify { mockFirebaseAuth.createUserWithEmailAndPassword(email, pass) }
        verify { registerActivity.startActivity(any()) }
    }


    @Test
    fun testCreateUserWithEmailAndPassword_Failure() {
        // Mock input values
        val name = "John"
        val email = "john@example.com"
        val pass = "password"
        val confirmPass = "password"

        // Mock FirebaseAuth createUserWithEmailAndPassword
        val mockException: Exception = mockk()
        every { mockFirebaseAuth.createUserWithEmailAndPassword(email, pass) } throws mockException

        // Call the method to be tested
        registerActivity.registerUser(name, email, pass, confirmPass)

        // Verify that the appropriate Toast message is shown
        verify { registerActivity.showToast("Exception message") }
    }

    @Test
    fun testCreateUserWithEmailAndPassword_PasswordMismatch() {
        // Mock input values
        val name = "John"
        val email = "john@example.com"
        val pass = "password"
        val confirmPass = "differentPassword"

        // Call the method to be tested
        registerActivity.registerUser(name, email, pass, confirmPass)

        // Verify that the appropriate Toast message is shown
        verify { registerActivity.showToast("Passwords do not match") }
    }

    @Test
    fun testCreateUserWithEmailAndPassword_EmptyFields() {
        // Mock input values
        val name = ""
        val email = ""
        val pass = ""
        val confirmPass = ""

        // Call the method to be tested
        registerActivity.registerUser(name, email, pass, confirmPass)

        // Verify that the appropriate Toast message is shown
        verify { registerActivity.showToast("Fields cannot be empty") }
    }

    @Test
    fun testGoToLogin() {
        val mockIntent = mockk<Intent>(relaxed = true)
        every { anyConstructed<Intent>().apply { this@apply.flags = any() } } returns mockIntent

        registerActivity.goToLogin()

        verify { registerActivity.startActivity(mockIntent) }
    }
}

