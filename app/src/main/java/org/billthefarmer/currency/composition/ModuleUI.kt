package org.billthefarmer.currency.composition

import org.billthefarmer.composition.scope.CompositionScopeDefault
import org.billthefarmer.composition.scope.factory
import org.billthefarmer.currency.ui.dashboard.DashboardViewModel
import org.billthefarmer.currency.ui.detail.DetailViewModel
import java.util.*

fun CompositionScopeDefault.Builder.uiModule() = apply {
    factory { DashboardViewModel() }
    factory { (c: Currency) -> DetailViewModel(c) }
}