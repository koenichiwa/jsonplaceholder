package com.kvw.jsonplaceholder.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.kvw.jsonplaceholder.business.repository.UserRepository
import com.kvw.jsonplaceholder.business.repository.UserRepositoryDefault
import com.kvw.jsonplaceholder.data.retrofit.UserService
import com.kvw.jsonplaceholder.data.room.AppDatabase
import com.kvw.jsonplaceholder.ui.userlist.UserListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {
    private const val JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com"
    private const val DATABASE_NAME = "jsonplaceholder_db"

    val repositoryModule = module {
        factory<UserRepository> { UserRepositoryDefault(get(), get()) }
    }

    val retrofitModule = module {
        single { provideRetrofit(provideGsonConverterFactory()) }
        factory<UserService> { get<Retrofit>().create(UserService::class.java) }
    }

    val roomModule = module {
        single { provideRoomDatabase(androidContext()) }
        factory { get<AppDatabase>().userDao() }
    }

    @ExperimentalCoroutinesApi
    val viewModelModule = module {
        viewModel { UserListViewModel(get()) }
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

    private fun provideRoomDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
}