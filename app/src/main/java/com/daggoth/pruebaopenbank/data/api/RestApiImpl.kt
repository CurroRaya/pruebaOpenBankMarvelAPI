package com.daggoth.pruebaopenbank.data.api

import com.daggoth.pruebaopenbank.data.model.CharacterResponse

class RestApiImpl(private val restApiService: RestApiService): RestApi {

    override suspend fun getCharacters(offset: Int): CharacterResponse {
        return restApiService.getCharacters(offset = offset)
    }
}