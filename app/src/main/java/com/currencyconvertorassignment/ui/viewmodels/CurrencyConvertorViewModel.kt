package com.currencyconvertorassignment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyconvertorassignment.data.CurrencyValue
import com.currencyconvertorassignment.data.entities.Currency
import com.currencyconvertorassignment.usecases.CurencyConvertorUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * view model class used by [com.currencyconvertorassignment.ui.CurrencyConvertorFragment]
 */
class CurrencyConvertorViewModel constructor(private val useCase: CurencyConvertorUseCases) :
    ViewModel() {

    //immutable to observe in the view
    val currencies: LiveData<State<List<Currency>>> get() = _currencies
    private val _currencies = MutableLiveData<State<List<Currency>>>()

    //immutable to observe in the view
    val currencyValues: LiveData<State<List<CurrencyValue>>> get() = _currencyValues
    private val _currencyValues = MutableLiveData<State<List<CurrencyValue>>>()

    //the current select currency
    val selectedCurrency: Currency? get() = _selectedCurrency
    private var _selectedCurrency: Currency? = null

    //the amount to convert in different currencies
    val amountToConvert: Double? get() = _amountToConvert
    private var _amountToConvert: Double? = null

    //at startup i'm setting all states to loading and empty in order to show desidered ui widgets
    //and loading all currencies list (from api or db)
    init {
        loadCurrencies()
    }

    fun loadCurrencies() {
        _currencies.value = State.loading()
        _currencyValues.value = State.empty()

        viewModelScope.launch {
            val state = withContext(Dispatchers.Default) {
                try {
                    val currenciesValue = useCase.getAllCurrencies()
                    State.success(currenciesValue)
                } catch (exception: Exception) {
                    State.error(exception.message)
                }
            }
            _currencies.value = state
        }

    }

    //this will be invoked when currency is selected from dropdown
    fun changeSelectedCurrency(selectedCurrency: Currency) {
        _selectedCurrency = selectedCurrency
        loadExchangeRates()
    }

    //this will be invoked when amount is changed inside text edit
    fun changeAmount(amount: Double?) {
        amount?.let {
            _amountToConvert = it
            loadExchangeRates()
        }
    }

    /**
     * load currencies rate, with error handling, i'm using [State] to handle the loading, error, success states.
     * error case is handled using custom exceptions. Take a look at [com.currencyconvertorassignment.data.exceptions.NetworkException]
     * and [com.currencyconvertorassignment.data.exceptions.GenericException]
     */
    private fun loadExchangeRates() {
        if (_selectedCurrency != null && _amountToConvert != null) {
            _currencyValues.value = State.loading()

            viewModelScope.launch {
                val state = withContext(Dispatchers.Default) {
                    try {
                        val currenciesValue = useCase.getRatesByCurrencyAndAmount(
                            _selectedCurrency!!,
                            _amountToConvert!!
                        )
                        State.success(currenciesValue)
                    } catch (exception: Exception) {
                        State.error(exception.message)
                    }
                }
                _currencyValues.value = state
            }
        }
    }

}