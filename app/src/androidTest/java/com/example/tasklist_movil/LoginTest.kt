import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tasklist_movil.Login
import com.example.tasklist_movil.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @Test
    fun testLogin_Successful() {
        val email = "romeramparo@gmail.com"
        val password = "123456"

        // Lanzar la actividad de inicio de sesión
        val scenario = ActivityScenario.launch(Login::class.java)

        // Ingresar el correo electrónico y contraseña
        Espresso.onView(ViewMatchers.withId(R.id.coreo_regis))
            .perform(ViewActions.typeText(email))
        Espresso.onView(ViewMatchers.withId(R.id.pass_regis))
            .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard())

        // Hacer clic en el botón de inicio de sesión
        Espresso.onView(ViewMatchers.withId(R.id.loginBtn))
            .perform(ViewActions.click())

        // Cerrar la actividad de inicio de sesión
        scenario.close()
    }

    @Test
    fun testLogin_EmptyFields() {
        // Lanzar la actividad de inicio de sesión
        val scenario = ActivityScenario.launch(Login::class.java)

        // Dejar los campos de correo electrónico y contraseña vacíos
        Espresso.onView(ViewMatchers.withId(R.id.loginBtn))
            .perform(ViewActions.click())

        // Verificar que se muestre el mensaje de campos vacíos
        Espresso.onView(ViewMatchers.withText("Los campos estan vacios"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Cerrar la actividad de inicio de sesión
        scenario.close()

    }

    @Test
    fun testLogin_InvalidCredentials() {
        val email = "yanp@hotmail.com"
        val password = "1234567"

        // Lanzar la actividad de inicio de sesión
        val scenario = ActivityScenario.launch(Login::class.java)

        // Ingresar el correo electrónico y contraseña incorrectos
        Espresso.onView(ViewMatchers.withId(R.id.coreo_regis))
            .perform(ViewActions.typeText(email))
        Espresso.onView(ViewMatchers.withId(R.id.pass_regis))
            .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard())

        // Hacer clic en el botón de inicio de sesión
        Espresso.onView(ViewMatchers.withId(R.id.loginBtn))
            .perform(ViewActions.click())

        // Agregar una pausa de 2 segundos (2000 milisegundos)
        Thread.sleep(2000)

        // Verificar que se muestre el mensaje de error de inicio de sesión
        Espresso.onView(ViewMatchers.withText("Los campos estan vacios"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Cerrar la actividad de inicio de sesión
        scenario.close()
    }


    @Test
    fun testGoToButtonRegister() {
        // Lanzar la actividad de inicio de sesión
        val scenario = ActivityScenario.launch(Login::class.java)

        // Hacer clic en el botón para ir a la pantalla de registro
        Espresso.onView(ViewMatchers.withId(R.id.tv_go_to_register))
            .perform(ViewActions.click())


        // Cerrar la actividad de inicio de sesión
        scenario.close()

    }
}