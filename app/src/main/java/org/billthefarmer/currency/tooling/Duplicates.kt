package org.billthefarmer.currency.tooling

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Duplicates(val other: KClass<*>)