package com.currencyconvertorassignment.data.repositories

import com.currencyconvertorassignment.data.entities.Currency
import com.currencyconvertorassignment.data.entities.ExchangeRate
import com.currencyconvertorassignment.data.dao.CurrencyDao
import com.currencyconvertorassignment.data.dao.ExchangeRateDao
//import javax.inject.Inject
//import javax.inject.Singleton

/**
 * repository to handle local data
 */
class LocalCurrencyRepository constructor(
    private val exchangeRateDao: ExchangeRateDao,
    private val currencyDao: CurrencyDao,
) {


    fun getCurrencies(): List<Currency> {
        return currencyDao.getAll()
    }

    fun getRatesWithTimeGreaterThan(minTime: Long): List<ExchangeRate> {
        return exchangeRateDao.getAllGreaterThanTimestamp(minTime)
    }

    fun saveCurrencies(currencies: List<Currency>) {
        currencyDao.insertAll(currencies)
    }

    fun saveExchangeRates(exchangeRateList: List<ExchangeRate>) {
        exchangeRateDao.insertAll(exchangeRateList)
    }

}