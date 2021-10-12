package com.daggoth.pruebaopenbank.intent.state

import com.daggoth.pruebaopenbank.data.model.CharacterResponse

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class LoadTasks(val characters: CharacterResponse): MainState()
    data class Error(val error: String?): MainState()
}
