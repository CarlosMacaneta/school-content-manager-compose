package com.cm.schoolcontentmanager.login.di

import com.cm.schoolcontentmanager.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel() }
}