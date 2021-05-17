package com.nixstudio.moviemax.utils.koin.modules

import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MovieMaxRepository(get(), get())
    }
}