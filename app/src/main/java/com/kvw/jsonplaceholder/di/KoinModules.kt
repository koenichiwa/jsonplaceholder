package com.kvw.jsonplaceholder.di

import com.google.gson.GsonBuilder
import com.kvw.jsonplaceholder.business.repository.UserRepository
import com.kvw.jsonplaceholder.business.repository.UserRepositoryDefault
import com.kvw.jsonplaceholder.data.retrofit.UserService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {
    private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com"

    val repositoryModule = module {
        factory<UserRepository> {
            UserRepositoryDefault(
                get()
            )
        }
    }

    val retrofitModule = module {
        single { provideRetrofit(provideGsonConverterFactory()) }
        factory<UserService> { get<Retrofit>().create(
            UserService::class.java) }
    }

    private fun provideGsonConverterFactory(): GsonConverterFactory {
        GsonBuilder()
            .create()
            .let { return GsonConverterFactory.create(it) }
    }

    private fun provideRetrofit(converterFactory: GsonConverterFactory): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(JSON_PLACEHOLDER_BASE_URL)
            .build()
    }
}