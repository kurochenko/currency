package wiki.depasquale.currency.tooling

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import wiki.depasquale.currency.presentation.view.main.LocalNavHostController
import wiki.depasquale.currency.screen.MainActivity

abstract class ComposeTest {

    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Before
    abstract fun prepare()

    @After
    open fun tearDown() = Unit

    fun inCompose(before: () -> Unit = {}, body: @Composable () -> Unit): TestableExpression {
        before()
        compose.setContent {
            CompositionLocalProvider(LocalNavHostController provides Mockito.mock(NavHostController::class.java)) {
                body()
            }
        }
        return TestableExpression()
    }

    infix fun TestableExpression.asserts(body: ComposeContentTestRule.() -> Unit) {
        body(compose)
    }

    class TestableExpression

}