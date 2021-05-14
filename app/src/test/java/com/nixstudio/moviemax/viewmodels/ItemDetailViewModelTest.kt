package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ItemDetailViewModelTest {

    private lateinit var viewModel: ItemDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTv: Observer<TvShowsEntity>

    @Mock
    private lateinit var repos: MovieMaxRepository

    @Before
    fun setUp() {
        viewModel = ItemDetailViewModel(repos)
    }

    @Test
    fun setCurrrentMovie_shouldPostCurrentMovieEntity() {
        val dummyMovies = DummyData.getMovieEntity()
        val movies = MutableLiveData<MovieEntity>()
        movies.value = dummyMovies

        `when`(repos.getMovieById(42069)).thenReturn(movies)
        val movieResults = viewModel.getCurrentMovie(42069).value
        verify(repos).getMovieById(42069)
        assertNotNull(movieResults)
        assertEquals(dummyMovies.title, movieResults?.title)

        viewModel.getCurrentMovie(42069).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun setCurrentTvShows_shouldPostCurrentTvShowsEntity() {
        val dummyTv = DummyData.getTvShowsEntity()
        val tvShows = MutableLiveData<TvShowsEntity>()
        tvShows.value = dummyTv

        `when`(repos.getTvShowsById(42069)).thenReturn(tvShows)
        val tvResults = viewModel.getCurrentTvShows(42069).value
        verify(repos).getTvShowsById(42069)
        assertNotNull(tvResults)
        assertEquals(dummyTv.name, tvResults?.name)

        viewModel.getCurrentTvShows(42069).observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }
}