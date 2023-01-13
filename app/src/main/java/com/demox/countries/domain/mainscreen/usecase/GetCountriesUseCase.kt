package com.demox.countries.domain.mainscreen.usecase

import com.demox.countries.domain.mainscreen.model.Country
import com.demox.countries.domain.mainscreen.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetCountriesUseCase(private val mainRepository: MainRepository) {
    suspend fun getCountries(): Flow<List<Country>> = mainRepository.getCountries()
}
