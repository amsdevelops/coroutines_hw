package ru.devivanov.coroutinesappexample

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.devivanov.coroutinesappexample.domain.Interactor
import ru.devivanov.coroutinesappexample.remote.DoggyApi

class App : Application() {
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/image/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val doggyApi = retrofit.create(DoggyApi::class.java)
        interactor = Interactor(doggyApi)

    }

    companion object {
        lateinit var instance: App
            private set
    }
}