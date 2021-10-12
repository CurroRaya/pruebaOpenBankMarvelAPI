package com.daggoth.pruebaopenbank.intent

sealed class MainIntent {
    object FetchCharacters: MainIntent()
    object GetMoreCharacters: MainIntent()
}