package com.nixstudio.moviemax.utils.koin.modules

import com.nixstudio.moviemax.data.sources.local.room.MovieMaxDatabase
import org.koin.dsl.module

val daoModule = module {
    single {
        get<MovieMaxDatabase>().movieMaxDao()
    }
}