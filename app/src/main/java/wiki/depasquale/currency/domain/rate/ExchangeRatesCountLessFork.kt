package wiki.depasquale.currency.domain.rate

import wiki.depasquale.currency.domain.model.ExchangeRate

class ExchangeRatesCountLessFork(
    private val source: ExchangeRates,
    private val otherwise: ExchangeRates,
    private val countLessOrEqual: Int
) : ExchangeRates {

    override fun getCurrentRates(): List<ExchangeRate> {
        var output = source.getCurrentRates()
        if (output.asSequence().distinctBy { it.time }.count() <= countLessOrEqual)
            output = otherwise.getCurrentRates()
        return output
    }

}