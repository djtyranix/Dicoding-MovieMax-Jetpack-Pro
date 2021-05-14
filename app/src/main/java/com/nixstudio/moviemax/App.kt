package com.nixstudio.moviemax

import android.app.Application
import com.nixstudio.moviemax.utils.koin.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    roomModule,
                    daoModule,
                    localDataSourceModule,
                    remoteDataSourceModule,
                    repositoryModule,
                    viewModelModule,
                    retrofitModule,
                    apiModule
                )
            )
        }
    }
}