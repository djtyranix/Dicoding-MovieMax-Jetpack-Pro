package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest : KoinTest {

    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<DiscoverTvResultsItem>>

    @Mock
    private lateinit var repos: MovieMaxRepository

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(repos)
    }

    @Test
    fun setTvShows() {
        val dummyTv = DummyData.getDiscoveryTv()
        val tvShow = MutableLiveData<List<DiscoverTvResultsItem>>()
        tvShow.value = dummyTv

        Mockito.`when`(repos.getDiscoveryTvShows()).thenReturn(tvShow)
        val tvResult = viewModel.getTvShows().value
        verify(repos).getDiscoveryTvShows()
        assertNotNull(tvResult)
        assertEquals(1, tvResult?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}