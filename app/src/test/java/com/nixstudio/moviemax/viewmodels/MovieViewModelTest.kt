package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        viewModel = MovieViewModel()
    }

    @Test
    fun setMovies() {
        val movies = DummyData.generateMovies()

        viewModel.setMovies()

        assertEquals(movies, viewModel.listMovieList)
    }
}