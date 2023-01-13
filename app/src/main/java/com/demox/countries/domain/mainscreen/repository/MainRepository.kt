package com.demox.countries.domain.mainscreen.repository

import com.demox.countries.domain.mainscreen.model.Country
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getCountries(): Flow<List<Country>>
}