package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<DiscoverMovieResultsItem>>

    @Mock
    private lateinit var repos: MovieMaxRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repos)
    }

    @Test
    fun setMovies() {
        val dummyMovies = DummyData.getDiscoveryMovies()
        val movies = MutableLiveData<List<DiscoverMovieResultsItem>>()
        movies.value = dummyMovies

        Mockito.`when`(repos.getDiscoveryMovie()).thenReturn(movies)
        val movieResults = viewModel.getMovies().value
        verify(repos).getDiscoveryMovie()
        Assert.assertNotNull(movieResults)
        Assert.assertEquals(1, movieResults?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}