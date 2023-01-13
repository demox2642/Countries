package com.demox.countries.data.network

import com.demox.countries.domain.mainscreen.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("/v2/all")
    suspend fun getCountries(): Response<List<Country>>
}
