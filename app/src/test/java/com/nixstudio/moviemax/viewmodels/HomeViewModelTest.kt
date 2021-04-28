package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify


class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        homeViewModel = HomeViewModel()
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