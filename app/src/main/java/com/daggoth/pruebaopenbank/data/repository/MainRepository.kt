package com.daggoth.pruebaopenbank.data.repository

import com.daggoth.pruebaopenbank.data.api.RestApi

class MainRepository(private val restApi: RestApi) {
    suspend fun getCharacters(offset: Int) = restApi.getCharacters(offset)
}