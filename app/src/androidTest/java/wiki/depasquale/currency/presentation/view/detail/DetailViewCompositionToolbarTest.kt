package wiki.depasquale.currency.presentation.view.detail

import androidx.compose.ui.test.*
import androidx.compose.ui.unit.dp
import org.junit.Test
import wiki.depasquale.currency.screen.detail.DetailViewModel
import wiki.depasquale.currency.tooling.ComposeTest
import java.util.*

class DetailViewCompositionToolbarTest : ComposeTest() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var view: DetailViewComposition

    override fun prepare() {
        viewModel = DetailViewModel(Currency.getInstance("USD"))
        view = DetailViewCompositionToolbar()
    }

    @Test
    fun hasTitle() = inCompose {
        view.Compose(model = viewModel)
    } asserts {
        onNodeWithTag("toolbar-title").assertExists()
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHeightIsAtLeast(25.dp)
            .assertHasNoClickAction()
    }

    @Test
    fun hasActionButton() = inCompose {
        view.Compose(model = viewModel)
    } asserts {
        onNodeWithContentDescription("go back").assertExists()
        onNodeWithTag("toolbar-action-button").assertExists()
            .assertIsDisplayed()
            .assertIsEnabled()
            .assertHeightIsAtLeast(48.dp)
            .assertHasClickAction()
    }

}