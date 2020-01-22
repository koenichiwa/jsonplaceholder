package com.kvw.jsonplaceholder

import android.app.Application
import com.kvw.jsonplaceholder.di.KoinModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class JsonPlaceholderApp : Application(){

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@JsonPlaceholderApp)
            modules(
                listOf(
                    KoinModules.retrofitModule,
                    KoinModules.repositoryModule,
                    KoinModules.viewModelModule,
                    KoinModules.roomModule
                )
            )
        }

        if (BuildConfig.DEBUG) { Timber.plant(Timber.DebugTree()) }
    }
}