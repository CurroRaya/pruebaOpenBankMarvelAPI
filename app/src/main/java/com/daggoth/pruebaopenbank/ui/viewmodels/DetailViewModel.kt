package com.daggoth.pruebaopenbank.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daggoth.pruebaopenbank.data.model.Results
import kotlinx.coroutines.launch


class DetailViewModel(private val character: Results?) : ViewModel() {

    var _character: MutableLiveData<Results> = MutableLiveData<Results>()
    val characterItem: LiveData<Results>
        get() = _character

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            setCharacter(character)
        }
    }

    fun setCharacter(character: Results?){
        _character.postValue(character)
    }
}