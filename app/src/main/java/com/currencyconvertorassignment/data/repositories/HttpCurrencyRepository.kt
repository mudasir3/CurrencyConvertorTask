package com.currencyconvertorassignment.data.repositories

import android.util.Log
import com.currencyconvertorassignment.data.dto.ExchangeRateDTO
import com.currencyconvertorassignment.data.exceptions.GenericException
import com.currencyconvertorassignment.data.exceptions.NetworkException
import com.currencyconvertorassignment.data.repositories.api.ApiInterface

/**
 * repository to handle external network call
 */
class HttpCurrencyRepository internal constructor() {

    private val tag = "httpRepository"

    fun fetchAllCurrencies(): Map<String, String> {

        Log.d(tag, "fetching currencies from api...")

        val apiInterface = ApiInterface.create()
        val request =
            apiInterface.getCurrencies()

        val response = request.execute()

        if (!response.isSuccessful) {
            Log.w(
                tag,
                "a network error occurred during comunication, throwing a NetworkException ${response.errorBody()}"
            )
            throw NetworkException()
        }

        val responseBody = response.body()
        if (responseBody == null) {
            Log.w(
                tag,
                "an unexpected error occurred during comunication, throwing a GenericException ${response.errorBody()}"
            )
            throw GenericException()
        }

        Log.d(tag, "call executhed, response body: $responseBody")

        return responseBody
    }


    fun fetchLatestExchangeRates(apiKey: String): ExchangeRateDTO {

        Log.d(tag, "fetching currencies from api...")

        val apiInterface = ApiInterface.create()
        val request =
            apiInterface.getLatestExchangeRate(apiKey)

        val response = request.execute()
        Log.i(tag, "call executhed, status code is ${response.code()}")

        if (!response.isSuccessful) {
            Log.w(
                tag,
                "a network error occurred during comunication, throwing a NetworkException ${response.errorBody()}"
            )
            throw NetworkException()
        }

        val rates = response.body()
        if (rates == null) {
            Log.w(
                tag,
                "an unexpected error occurred during comunication, throwing a GenericException ${response.errorBody()}"
            )
            throw GenericException()
        }
        Log.d(tag, "call executhed, response body: $rates")

        return rates
    }

}