package com.daggoth.pruebaopenbank.data.api

import com.daggoth.pruebaopenbank.BuildConfig
import com.daggoth.pruebaopenbank.data.api.utils.ApiConectionUtils
import com.daggoth.pruebaopenbank.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String = ApiConectionUtils.ts,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String = ApiConectionUtils.hash(),
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int = 50
    ): CharacterResponse
}