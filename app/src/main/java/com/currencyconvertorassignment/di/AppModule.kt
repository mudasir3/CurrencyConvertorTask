package com.currencyconvertorassignment.di

import android.content.Context
import com.currencyconvertorassignment.data.dao.CurrencyDao
import com.currencyconvertorassignment.data.dao.ExchangeRateDao
import com.currencyconvertorassignment.data.database.AppDatabase
import com.currencyconvertorassignment.data.repositories.HttpCurrencyRepository
import com.currencyconvertorassignment.data.repositories.LocalCurrencyRepository
import com.currencyconvertorassignment.ui.viewmodels.CurrencyConvertorViewModel
import com.currencyconvertorassignment.usecases.CurencyConvertorUseCases
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single { provideAppDatabase(androidContext()) }
    single { provideCurrencyDao(get()) }
    single { provideExchangeRateDao(get()) }
    single { HttpCurrencyRepository() }
    single { LocalCurrencyRepository(get(),get()) }

    factory { provideCurrencyUseCases(get(),get()) }
    viewModel{ provideViewModel(get()) }

}

fun provideAppDatabase(context: Context): AppDatabase {
    return AppDatabase.getInstance(context)
}

fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDao {
    return appDatabase.currencyDao()
}

fun provideExchangeRateDao(appDatabase: AppDatabase): ExchangeRateDao {
    return appDatabase.exchangeRateDao()
}

fun provideViewModel(useCase: CurencyConvertorUseCases): CurrencyConvertorViewModel {
    return CurrencyConvertorViewModel(useCase)
}

fun provideCurrencyUseCases(httpRepository: HttpCurrencyRepository,localRepository: LocalCurrencyRepository): CurencyConvertorUseCases {
    return CurencyConvertorUseCases(httpRepository,localRepository)
}
