package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class ItemDetailViewModelTest {

    private lateinit var viewModel: ItemDetailViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        viewModel = ItemDetailViewModel()
    }

    @Test
    fun setCurrrentMovie_shouldPostCurrentMovieEntity() {
        val movie = MovieEntity(
            0,
            "Interstellar",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
            2015,
            "Sci-Fi",
            "The adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.",
            "02:49:00"
        )

        viewModel.setCurrrentMovie(movie)

        assertEquals(movie, viewModel.currentMovieItem)
    }

    @Test
    fun setCurrentTvShows_shouldPostCurrentTvShowsEntity() {
        val tvShows = TvShowsEntity(
            0,
            "The Falcon and the Winter Soldier",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            2021,
            "Action",
            1,
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
        )

        viewModel.setCurrentTvShows(tvShows)

        assertEquals(tvShows, viewModel.currentTvShowsItem)
    }

    @Test
    fun getMode_shouldReturnCorrectSetMode() {
        val mode = 1

        viewModel._mode = mode

        assertEquals(mode, viewModel.getMode())
    }
}