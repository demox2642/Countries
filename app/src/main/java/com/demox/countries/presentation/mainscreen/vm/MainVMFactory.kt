package com.demox.countries.presentation.mainscreen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demox.countries.data.mainscreen.repository.MainRepositoryImpl
import com.demox.countries.data.network.NetworkRetrofit
import com.demox.countries.domain.mainscreen.usecase.GetCountriesUseCase

class MainVMFactory : ViewModelProvider.Factory {
    private val mainRepository by lazy(LazyThreadSafetyMode.NONE) {
        MainRepositoryImpl(countriesApi = NetworkRetrofit.api)
    }

    private val getCountriesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetCountriesUseCase(mainRepository = mainRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCountriesUseCase) as T
    }
}
