package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    private lateinit var viewModel: SearchViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<CombinedResultEntity>>

    @Mock
    private lateinit var repos: MovieMaxRepository

    @Before
    fun setUp() {
        viewModel = SearchViewModel(repos)
    }

    @Test
    fun setSearch_shouldReturnSearchList() {
        val dummySearch = DummyData.getCombinedResult()
        val searchList = MutableLiveData<List<CombinedResultEntity>>()
        searchList.value = dummySearch

        Mockito.`when`(repos.searchByString("Test")).thenReturn(searchList)
        val searchRes = viewModel.getSearchResults("Test").value
        verify(repos).searchByString("Test")
        assertNotNull(searchRes)
        assertEquals(2, searchRes?.size)

        viewModel.getSearchResults("Test").observeForever(observer)
        verify(observer).onChanged(dummySearch)
    }
}