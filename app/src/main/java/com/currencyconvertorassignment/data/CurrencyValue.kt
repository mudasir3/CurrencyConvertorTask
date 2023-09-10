package com.currencyconvertorassignment.data

/**
 * model class to handle currencies value in UI, used mainly by adapter [com.currencyconvertorassignment.ui.adapters.CurrencyConvertorAdapter]
 */
data class CurrencyValue(

    val symbol: String,

    val amount: Double,
)