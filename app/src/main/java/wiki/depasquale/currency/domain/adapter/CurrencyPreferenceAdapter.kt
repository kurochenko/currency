package wiki.depasquale.currency.domain.adapter

import androidx.annotation.Keep
import wiki.depasquale.currency.domain.preference.PreferenceAdapter
import java.util.*

class CurrencyPreferenceAdapter @Keep constructor() : PreferenceAdapter {

    override fun fromPreference(input: Any?): Currency? {
        if (input == null)
            return null

        return fromPreference(input as String)
    }

    override fun toPreference(input: Any?): Any? {
        return toPreference(input as Currency)
    }

    private fun fromPreference(input: String): Currency {
        return Currency.getInstance(input)
    }

    private fun toPreference(input: Currency): String {
        return input.currencyCode
    }

}