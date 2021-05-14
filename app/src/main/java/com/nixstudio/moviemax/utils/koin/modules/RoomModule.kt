package com.nixstudio.moviemax.utils.koin.modules

import androidx.room.Room
import com.nixstudio.moviemax.data.sources.local.room.MovieMaxDatabase
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), MovieMaxDatabase::class.java, "moviemax-db").build()
    }
}