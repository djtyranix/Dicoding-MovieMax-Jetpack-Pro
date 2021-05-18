package com.nixstudio.moviemax.utils.koin.modules

import android.app.Application
import androidx.room.Room
import com.nixstudio.moviemax.data.sources.local.room.MovieMaxDao
import com.nixstudio.moviemax.data.sources.local.room.MovieMaxDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): MovieMaxDatabase {
        return Room.databaseBuilder(application, MovieMaxDatabase::class.java, "moviemax-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: MovieMaxDatabase): MovieMaxDao {
        return database.movieMaxDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}