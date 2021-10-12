package com.daggoth.pruebaopenbank.data.api

import com.daggoth.pruebaopenbank.data.model.CharacterResponse

interface RestApi {
    suspend fun getCharacters(offset: Int): CharacterResponse
}