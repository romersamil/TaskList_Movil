package com.example.tasklist_movil

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.rule.ActivityTestRule
import com.google.common.base.Verify.verify
import com.google.firebase.auth.FirebaseAuth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterActivityTest {


    internal class RegisterActivityTest {

        private lateinit var scenario: ActivityScenario<RegisterActivity>
        private lateinit var registerActivity: RegisterActivity
        private lateinit var mockFirebaseAuth: FirebaseAuth

        @Before
        fun setup() {
            // Mock FirebaseAuth instance
            mockFirebaseAuth = mockk(relaxed = true)
            every { FirebaseAuth.getInstance() } returns mockFirebaseAuth

            // Launch the activity using ActivityScenario
            scenario = ActivityScenario.launch(RegisterActivity::class.java)

            // Get the activity instance
            scenario.onActivity { activity ->
                registerActivity = activity
                registerActivity.firebaseAuth = mockFirebaseAuth
                registerActivity.binding = mockk(relaxed = true)
            }
        }

        @Test
        fun testCheck() {
            Assert.assertTrue(true)
        }

        @Test
        fun testCreateUserWithEmailAndPassword_Successful() {
            val name = "John"
            val email = "john@example.com"
            val pass = "password"
            val confirmPass = "password"

            every { mockFirebaseAuth.createUserWithEmailAndPassword(email, pass) } returns mockk()

            registerActivity.registerUser(name, email, pass, confirmPass)

            verify { mockFirebaseAuth.createUserWithEmailAndPassword(email, pass) }
            verify { registerActivity.startActivity(any()) }
        }

        @Test
        fun testCreateUserWithEmailAndPassword_Failure() {
            val name = "John"
            val email = "john@example.com"
            val pass = "password"
            val confirmPass = "password"

            val mockException: Exception = mockk()
            every {
                mockFirebaseAuth.createUserWithEmailAndPassword(
                    email,
                    pass
                )
            } throws mockException

            registerActivity.registerUser(name, email, pass, confirmPass)

            verify { registerActivity.showToast("Exception message") }
        }

        @Test
        fun testCreateUserWithEmailAndPassword_PasswordMismatch() {
            val name = "John"
            val email = "john@example.com"
            val pass = "password"
            val confirmPass = "differentPassword"

            registerActivity.registerUser(name, email, pass, confirmPass)

            verify { registerActivity.showToast("Passwords do not match") }
        }

        @Test
        fun testCreateUserWithEmailAndPassword_EmptyFields() {
            val name = ""
            val email = ""
            val pass = ""
            val confirmPass = ""

            registerActivity.registerUser(name, email, pass, confirmPass)

            verify { registerActivity.showToast("Fields cannot be empty") }
        }
    }
}
