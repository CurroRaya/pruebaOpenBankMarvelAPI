package com.daggoth.pruebaopenbank.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daggoth.pruebaopenbank.data.model.Results
import com.daggoth.pruebaopenbank.ui.viewmodels.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DetailViewModelFactory(private val character: Results?) : ViewModelProvider.NewInstanceFactory(){
    @ExperimentalCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailViewModel(character) as T
}