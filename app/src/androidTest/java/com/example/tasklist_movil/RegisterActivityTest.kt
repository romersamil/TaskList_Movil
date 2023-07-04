import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tasklist_movil.Login
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.tasklist_movil.R
import com.example.tasklist_movil.RegisterActivity
import org.junit.Assert

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

        // Check if the activity is finished and user is redirected to Login activity
        /*scenario.onActivity { activity ->
            Assert.assertEquals(activity.intent.component?.className, Login::class.java.name)*/

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

        // Check if the error message is displayed
        Espresso.onView(ViewMatchers.withText("LAS CONTRASENA NO ES IGUAL A LA CONFIRMACION"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
