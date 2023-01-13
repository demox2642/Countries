package com.demox.countries.presentation.mainscreen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demox.countries.domain.mainscreen.model.Country
import com.demox.countries.domain.mainscreen.usecase.GetCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {
    private val _countriesList = MutableStateFlow<List<Country>>(emptyList())
    val countryList = _countriesList.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        update()
    }

    fun update() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _errorMessage.value = ""
                getCountriesUseCase.getCountries().collectLatest {
                    _countriesList.value = it
                }
            } catch (e: Throwable) {
                _errorMessage.value = e.message!!
            } finally {
                _loading.value = false
            }
        }
    }

    fun cleanData() {
        _errorMessage.value = ""
    }
}
