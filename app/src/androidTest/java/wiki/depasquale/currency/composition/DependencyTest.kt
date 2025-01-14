package wiki.depasquale.currency.composition

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.BeforeClass
import org.junit.Test
import wiki.depasquale.composition.extra.ParametersNotSpecifiedException
import wiki.depasquale.currency.domain.startup.StartupComposition

class DependencyTest {

    companion object {

        @JvmStatic
        @BeforeClass
        fun createDependencies() {
            val context = InstrumentationRegistry.getInstrumentation().context
            StartupComposition().create(context)
        }

    }

    @Test
    fun testAllDependenciesResolve() {
        val compositor = Dependency()
        val entries = compositor.getKeys()

        for ((type, aliases) in entries) {
            for (alias in aliases) {
                try {
                    compositor.get(type, alias)
                } catch (e: ParametersNotSpecifiedException) {
                    println(e.message + " for type $type")
                }
            }
        }
    }

}