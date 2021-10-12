package com.daggoth.pruebaopenbank.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daggoth.pruebaopenbank.data.api.RestApi
import com.daggoth.pruebaopenbank.data.repository.MainRepository
import com.daggoth.pruebaopenbank.ui.viewmodels.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ListViewModelFactory(private val restApi: RestApi) : ViewModelProvider.Factory{

    @ExperimentalCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java))
            return ListViewModel(MainRepository(restApi)) as T

        throw IllegalArgumentException("Unknown class name")
    }
}