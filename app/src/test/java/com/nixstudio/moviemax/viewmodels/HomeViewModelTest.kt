package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nixstudio.moviemax.utils.repositoryModule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {

        homeViewModel = HomeViewModel(KoinComponent.get())
    }

    @Test
    fun setMovies_shouldPostMoviesList() {
        val movies = DummyData.generateLatestMovies()
        homeViewModel.setMovies()

        assertEquals(movies, homeViewModel.listMovieList)
    }

    @Test
    fun setTvShows_shouldPostTvList() {
        val tvShows = DummyData.generateLatestTvShows()
        homeViewModel.setTvShows()

        assertEquals(tvShows, homeViewModel.listTvList)
    }
}