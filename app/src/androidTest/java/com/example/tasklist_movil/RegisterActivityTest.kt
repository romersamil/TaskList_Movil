import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tasklist_movil.Login
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.tasklist_movil.R
import com.example.tasklist_movil.RegisterActivity
import org.hamcrest.BaseMatcher
import org.junit.Assert
import org.junit.runner.Description

@RunWith(AndroidJUnit4::class)
class RegisterActivityTest {

    private lateinit var firebaseAuth: FirebaseAuth

    @Before
    fun setup() {
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
        firebaseAuth = FirebaseAuth.getInstance()
    }

    @Test
    fun testRegisterUser_SuccessfulRegistration() {
        val name = "John Doe"
        val email = "john.doe@example.com"
        val password = "password"
        val confirmPass = "password"

        // Start the activity
        val scenario = ActivityScenario.launch(RegisterActivity::class.java)

        // Enter user details
        Espresso.onView(ViewMatchers.withId(R.id.Name_Edit_Text))
            .perform(ViewActions.typeText(name))
        Espresso.onView(ViewMatchers.withId(R.id.Email_Edit_Text))
            .perform(ViewActions.typeText(email))
        Espresso.onView(ViewMatchers.withId(R.id.Password_Edit_Text))
            .perform(ViewActions.typeText(password))
        Espresso.onView(ViewMatchers.withId(R.id.Confir_Password_Edit_Text))
            .perform(ViewActions.typeText(confirmPass))

        // Click the Register button
        Espresso.onView(ViewMatchers.withId(R.id.Registrarsebtn))
            .perform(ViewActions.click())

        // Wait for registration to complete using IdlingResource or other synchronization methods

    }


    @Test
    fun testRegisterUser_PasswordMismatch() {
        val name = "John Doe"
        val email = "john.doe@example.com"
        val password = "password"
        val confirmPass = "differentpassword"

        // Start the activity
        val scenario = ActivityScenario.launch(RegisterActivity::class.java)

        // Enter user details
        Espresso.onView(ViewMatchers.withId(R.id.Name_Edit_Text))
            .perform(ViewActions.typeText(name))
        Espresso.onView(ViewMatchers.withId(R.id.Email_Edit_Text))
            .perform(ViewActions.typeText(email))
        Espresso.onView(ViewMatchers.withId(R.id.Password_Edit_Text))
            .perform(ViewActions.typeText(password))
        Espresso.onView(ViewMatchers.withId(R.id.Confir_Password_Edit_Text))
            .perform(ViewActions.typeText(confirmPass))

        // Click the Register button
        Espresso.onView(ViewMatchers.withId(R.id.Registrarsebtn))
            .perform(ViewActions.click())

        // Wait for the animation and visibility to be updated
        Thread.sleep(2000) // Adjust this delay as needed

        // Check if the errorTextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.errorTextView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun testGoToLogin() {
        // Start the activity
        val scenario = ActivityScenario.launch(RegisterActivity::class.java)

        // Click the "Go to Login" TextView
        Espresso.onView(ViewMatchers.withId(R.id.tv_go_to_login))
            .perform(ViewActions.click())

        // Close the activity
        scenario.close()
    }

    @Test
    fun testRegisterUser_EmptyFields() {
        val name = ""
        val email = ""
        val password = ""
        val confirmPass = ""

        // Start the activity
        val scenario = ActivityScenario.launch(RegisterActivity::class.java)

        // Enter user details
        Espresso.onView(ViewMatchers.withId(R.id.Name_Edit_Text))
            .perform(ViewActions.typeText(name))
        Espresso.onView(ViewMatchers.withId(R.id.Email_Edit_Text))
            .perform(ViewActions.typeText(email))
        Espresso.onView(ViewMatchers.withId(R.id.Password_Edit_Text))
            .perform(ViewActions.typeText(password))
        Espresso.onView(ViewMatchers.withId(R.id.Confir_Password_Edit_Text))
            .perform(ViewActions.typeText(confirmPass))

        // Click the Register button
        Espresso.onView(ViewMatchers.withId(R.id.Registrarsebtn))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText("Hay campos vacios"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Cerrar la actividad de inicio de sesión
        scenario.close()

    }
}