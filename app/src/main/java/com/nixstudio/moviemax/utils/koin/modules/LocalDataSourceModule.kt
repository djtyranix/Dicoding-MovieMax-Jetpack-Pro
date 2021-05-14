package com.nixstudio.moviemax.utils.koin.modules

import com.nixstudio.moviemax.data.sources.local.LocalDataSource
import org.koin.dsl.module

val localDataSourceModule = module {
    single {
        LocalDataSource(get())
    }
}