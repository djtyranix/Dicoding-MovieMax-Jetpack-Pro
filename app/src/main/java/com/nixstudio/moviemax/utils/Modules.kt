package com.nixstudio.moviemax.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nixstudio.moviemax.api.ApiService
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import com.nixstudio.moviemax.models.sources.remote.RemoteDataSource
import com.nixstudio.moviemax.viewmodels.HomeViewModel
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel
import com.nixstudio.moviemax.viewmodels.MovieViewModel
import com.nixstudio.moviemax.viewmodels.TvShowsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        MovieViewModel(get())
    }

    viewModel {
        TvShowsViewModel(get())
    }

    viewModel {
        ItemDetailViewModel(get())
    }
}

val remoteDataSourceModule = module {
    single {
        RemoteDataSource(get())
    }
}

val repositoryModule = module {
    single {
        MovieMaxRepository(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}