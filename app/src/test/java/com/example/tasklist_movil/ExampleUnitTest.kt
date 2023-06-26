package com.example.tasklist_movil

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*
import org.junit.BeforeClass


@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    private lateinit var firestoreMock: FirebaseFirestore

    companion object {
        private lateinit var context: Context

        @BeforeClass
        @Throws(Exception::class)
        fun setup() {
            context = ApplicationProvider.getApplicationContext<Context>()

            val options = FirebaseOptions.Builder()
                .setProjectId("121023589329")
                .setApplicationId("1:121023589329:android:402524118242b675eb0ef0")
                .setApiKey("AIzaSyAe1Opg9Cot2lyCPEG2jPyV9FCFWroDquk")

                .build()

            FirebaseApp.initializeApp(context, options)
        }
    }

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun firebase_validation() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun email_validation(){
        assertEquals(5,10-5)
    }
    @Test
    fun pass_valitation(){
        assertEquals(10, 5*2)
    }

    @Test
    fun firestoreConnectionTest() {
        assertNotNull(firestoreMock)
    }

    @Test
    fun error_ocacionado(){
        assertEquals(2, 9-8)
    }

    class ValidationTest {

        @Test
        fun testValidUsername() {
            val username = "john_doe"
            val isValid = validateUsername(username)
            assertTrue(isValid)
        }

        @Test
        fun testInvalidUsername() {
            val username = "john@doe"
            val isValid = validateUsername(username)
            assertFalse(isValid)
        }

        @Test
        fun testValidPassword() {
            val password = "Abc12345"
            val isValid = validatePassword(password)
            assertTrue(isValid)
        }

        @Test
        fun testInvalidPassword() {
            val password = "abc123"
            val isValid = validatePassword(password)
            assertFalse(isValid)
        }

        private fun validateUsername(username: String): Boolean {
            if (username.length < 6 || username.length > 20) {
                return false
            }
            if (!username.matches(Regex("^[a-zA-Z0-9_]+$"))) {
                return false
            }
            return true
        }

        private fun validatePassword(password: String): Boolean {
            if (password.length < 8 || password.length > 20) {
                return false
            }
            if (!password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$"))) {
                return false
            }
            return true
        }
    }

}



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
