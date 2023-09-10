package com.currencyconvertorassignment

import android.app.Application
import com.currencyconvertorassignment.di.appModule
import org.koin.core.context.startKoin

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class CurrencyConvertorApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CurrencyConvertorApplication)
            androidLogger()
            modules(listOf(appModule
            ))}
    }
}