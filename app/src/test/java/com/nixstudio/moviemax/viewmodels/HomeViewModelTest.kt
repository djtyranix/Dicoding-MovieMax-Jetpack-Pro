package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResponse
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.utils.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeViewModelTest: KoinTest {

    private val homeViewModel by inject<HomeViewModel>()
    private val movieMaxRepository by inject<MovieMaxRepository>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(listOf(remoteDataSourceModule, repositoryModule, viewModelModule, retrofitModule, apiModule))
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovie: Observer<List<DiscoverMovieResultsItem>>

    @Mock
    private lateinit var observerTv: Observer<List<DiscoverTvResultsItem>>

    @Mock
    private lateinit var observerTrending: Observer<List<CombinedResultEntity>>

    @Test
    fun setMovies_shouldPostMoviesList() {
        val movies = MutableLiveData<List<DiscoverMovieResultsItem>>()
        val discoverMovieResponse = movieMaxRepository.getDiscoveryMovie()


        `when`()
        homeViewModel.setMovies()

        assertEquals(movies, homeViewModel.listMovieList)
    }

    @Test
    fun setTvShows_shouldPostTvList() {
        val tvShows = DummyData.generateLatestTvShows()
        homeViewModel.setTvShows()

        assertEquals(tvShows, homeViewModel.listTvList)
    }

    @Test
    fun setTrending_shouldPostTrendingList() {

    }
}