package com.kvw.jsonplaceholder.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.kvw.jsonplaceholder.business.model.User
import com.kvw.jsonplaceholder.business.repository.AlbumRepository
import com.kvw.jsonplaceholder.business.repository.AlbumRepositoryDefault
import com.kvw.jsonplaceholder.business.repository.UserRepository
import com.kvw.jsonplaceholder.business.repository.UserRepositoryDefault
import com.kvw.jsonplaceholder.data.retrofit.AlbumService
import com.kvw.jsonplaceholder.data.retrofit.UserService
import com.kvw.jsonplaceholder.data.room.AppDatabase
import com.kvw.jsonplaceholder.ui.userdetail.UserDetailViewModel
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
        factory<AlbumRepository> { AlbumRepositoryDefault(get(), get()) }
    }

    val retrofitModule = module {
        single { provideRetrofit(provideGsonConverterFactory()) }
        factory { get<Retrofit>().create(UserService::class.java) }
        factory { get<Retrofit>().create(AlbumService::class.java) }
    }

    val roomModule = module {
        single { provideRoomDatabase(androidContext()) }
        factory { get<AppDatabase>().userDao() }
        factory { get<AppDatabase>().albumDao() }
    }

    @ExperimentalCoroutinesApi
    val viewModelModule = module {
        viewModel { UserListViewModel(get()) }
        viewModel { (user: User) -> UserDetailViewModel(user, get()) }
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
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}