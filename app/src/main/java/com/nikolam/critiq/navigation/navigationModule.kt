package com.nikolam.critiq.navigation

import org.koin.dsl.module

val navigationModule = module {
    single {NavManager()}
}