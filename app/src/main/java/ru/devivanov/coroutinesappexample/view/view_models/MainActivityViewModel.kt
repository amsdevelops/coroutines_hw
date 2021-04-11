package ru.devivanov.coroutinesappexample.view.view_models

import androidx.lifecycle.ViewModel
import ru.devivanov.coroutinesappexample.App

class MainActivityViewModel : ViewModel() {
    private val interactor = App.instance.interactor

    suspend fun getDoggyImageUrl() = interactor.getDoggy()

}