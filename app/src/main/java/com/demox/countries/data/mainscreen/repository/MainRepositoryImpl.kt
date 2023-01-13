package com.demox.countries.data.mainscreen.repository

import com.demox.countries.data.network.CountriesApi
import com.demox.countries.domain.mainscreen.model.Country
import com.demox.countries.domain.mainscreen.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepositoryImpl(private val countriesApi: CountriesApi) : MainRepository {
    override suspend fun getCountries(): Flow<List<Country>> {
        val response = countriesApi.getCountries().body()!!
        return flow { emit(response) }.flowOn(Dispatchers.IO)
    }
}
