package com.daggoth.pruebaopenbank.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daggoth.pruebaopenbank.data.repository.MainRepository
import com.daggoth.pruebaopenbank.intent.MainIntent
import com.daggoth.pruebaopenbank.intent.state.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ListViewModel(private val repository: MainRepository) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    private var offset = 0
    private var incrementOffset = 50

    val mainState: StateFlow<MainState>
        get() = _mainState

    init {
        handleIntent()
    }

    @ExperimentalCoroutinesApi
    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it) {
                    is MainIntent.FetchCharacters -> {
                        fetchCharacters()
                    }
                    is MainIntent.GetMoreCharacters -> {
                        offset += incrementOffset
                        fetchCharacters()
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private suspend fun fetchCharacters() {
        viewModelScope.launch {
            _mainState.value = MainState.Loading

            _mainState.value = try {
                MainState.LoadTasks(repository.getCharacters(offset))
            } catch (e: Exception) {
                MainState.Error(e.message)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun setIdleFragment() {
        viewModelScope.launch {
            _mainState.value = MainState.Idle
        }
    }
}