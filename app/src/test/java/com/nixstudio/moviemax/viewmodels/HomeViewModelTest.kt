package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovie: Observer<List<DiscoverMovieResultsItem>>

    @Mock
    private lateinit var observerTv: Observer<List<DiscoverTvResultsItem>>

    @Mock
    private lateinit var observerTrending: Observer<List<CombinedResultEntity>>

    @Mock
    private lateinit var repos: MovieMaxRepository

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repos)
    }

    @Test
    fun setMovies_shouldPostMoviesList() {
        val dummyMovies = DummyData.getDiscoveryMovies()
        val movies = MutableLiveData<List<DiscoverMovieResultsItem>>()
        movies.value = dummyMovies

        `when`(repos.getDiscoveryMovie()).thenReturn(movies)
        val movieResults = viewModel.getMovies().value
        verify(repos).getDiscoveryMovie()
        assertNotNull(movieResults)
        assertEquals(1, movieResults?.size)

        viewModel.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun setTvShows_shouldPostTvList() {
        val dummyTv = DummyData.getDiscoveryTv()
        val tvShow = MutableLiveData<List<DiscoverTvResultsItem>>()
        tvShow.value = dummyTv

        `when`(repos.getDiscoveryTvShows()).thenReturn(tvShow)
        val tvResult = viewModel.getTvShows().value
        verify(repos).getDiscoveryTvShows()
        assertNotNull(tvResult)
        assertEquals(1, tvResult?.size)

        viewModel.getTvShows().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }

    @Test
    fun setTrending_shouldPostTrendingList() {
        val dummyTrending = DummyData.getCombinedResult()
        val trending = MutableLiveData<List<CombinedResultEntity>>()
        trending.value = dummyTrending

        `when`(repos.getTrendingToday()).thenReturn(trending)
        val trendingRes = viewModel.getTrending().value
        verify(repos).getTrendingToday()
        assertNotNull(trendingRes)
        assertEquals(2, trendingRes?.size)

        viewModel.getTrending().observeForever(observerTrending)
        verify(observerTrending).onChanged(dummyTrending)
    }
}