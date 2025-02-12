package wiki.depasquale.currency.presentation.view.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wiki.depasquale.currency.domain.model.ExchangeRate
import wiki.depasquale.currency.domain.rate.ExchangeRates
import wiki.depasquale.currency.presentation.adapter.DaySnapshotAdapter
import wiki.depasquale.currency.screen.detail.DetailViewModel
import java.util.*

class DetailViewCompositionContentLoader(
    private val source: DetailViewComposition,
    private val rates: ExchangeRates,
    private val adapter: DaySnapshotAdapter
) : DetailViewComposition {

    @Composable
    override fun Compose(model: DetailViewModel) {
        source.Compose(model = model)
        LaunchedEffect(model) {
            model.rates.value = getRates(model.currency)
        }
    }

    private suspend fun getRates(currency: Currency) = withContext(Dispatchers.IO) {
        getExchangeRates()
            .asSequence()
            .filter { it.currency == currency }
            .map(adapter::adapt)
            .toList()
            .asReversed()
    }

    private suspend fun getExchangeRates(): List<ExchangeRate> {
        return withContext(Dispatchers.IO) {
            rates.getCurrentRates()
        }
    }

}