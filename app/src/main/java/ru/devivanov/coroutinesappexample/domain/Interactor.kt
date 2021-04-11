package ru.devivanov.coroutinesappexample.domain

import retrofit2.Response
import ru.devivanov.coroutinesappexample.Entity.DoggyResponse
import ru.devivanov.coroutinesappexample.remote.DoggyApi

class Interactor(private val api: DoggyApi) {
    suspend fun getDoggy() = api.getRandomDog()

}