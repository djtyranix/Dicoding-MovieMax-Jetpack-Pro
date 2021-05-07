package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun setTvShows() {
        val tvShows = DummyData.generateTvShows()

        viewModel.setTvShows()

        assertEquals(tvShows, viewModel.listTvList)
    }
}