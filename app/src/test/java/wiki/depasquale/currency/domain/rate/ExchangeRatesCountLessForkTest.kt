package wiki.depasquale.currency.domain.rate

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import wiki.depasquale.currency.domain.model.ExchangeRate
import wiki.depasquale.currency.tooling.MockableTest
import java.util.*
import kotlin.random.Random.Default.nextDouble

class ExchangeRatesCountLessForkTest : MockableTest() {

    private lateinit var rates: ExchangeRatesCountLessFork

    @Mock
    lateinit var first: ExchangeRates

    @Mock
    lateinit var second: ExchangeRates

    @Before
    override fun prepare() {
        super.prepare()
        rates = ExchangeRatesCountLessFork(first, second, 0)
    }

    @Test
    fun `invokes only first if first is not empty`() {
        val firstRates = listOf(ExchangeRate(Currency.getInstance("USD"), nextDouble(), Date()))
        Mockito
            .`when`(first.getCurrentRates())
            .thenReturn(firstRates)
        Mockito
            .`when`(second.getCurrentRates())
            .thenThrow(IllegalStateException())

        val rates = rates.getCurrentRates()

        assertThat(rates).isSameInstanceAs(firstRates)
    }

    @Test
    fun `invokes first and second if first is empty`() {
        val secondRates = listOf(ExchangeRate(Currency.getInstance("USD"), nextDouble(), Date()))
        Mockito
            .`when`(first.getCurrentRates())
            .thenReturn(emptyList())
        Mockito
            .`when`(second.getCurrentRates())
            .thenReturn(secondRates)

        val rates = rates.getCurrentRates()

        assertThat(rates).isSameInstanceAs(secondRates)
    }

}