package ru.devivanov.coroutinesappexample.remote

import retrofit2.http.GET
import ru.devivanov.coroutinesappexample.Entity.DoggyResponse

interface DoggyApi {
    @GET("random")
    suspend fun getRandomDog(): DoggyResponse
}