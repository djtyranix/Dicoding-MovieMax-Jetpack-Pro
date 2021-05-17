package com.nixstudio.moviemax.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.utils.MediaType
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteEntity>>

    @Mock
    private lateinit var intObserver: Observer<Int>

    @Mock
    private lateinit var repos: MovieMaxRepository

    @Mock
    private lateinit var pagedList: PagedList<FavoriteEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(repos)
    }

    inner class ListDataSource (private val items: List<FavoriteEntity>): PositionalDataSource<FavoriteEntity>() {
        override fun loadInitial (params: LoadInitialParams, callback: LoadInitialCallback<FavoriteEntity>) {
            callback.onResult (items, 0, items.size)
        }
        override fun loadRange (params: LoadRangeParams, callback: LoadRangeCallback<FavoriteEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult (items.subList (start, end))
        }
    }

    @Test
    fun getFavorites_shouldReturnFavoritesList() {
        val dummyFav = pagedList
        `when`(dummyFav.size).thenReturn(2)
        val favorites = MutableLiveData<PagedList<FavoriteEntity>>()
        favorites.value = dummyFav

        `when`(repos.getAllFavorites()).thenReturn(favorites)
        val favoriteEntities = viewModel.getFavorites().value
        verify(repos).getAllFavorites()
        assertNotNull(favoriteEntities)
        assertEquals(2, favoriteEntities.size)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(dummyFav)
    }

    @Test
    fun getFavoritesByMediaType_Movie_shouldReturnFavoritesList() {
        val dummyFav = pagedList
        `when`(dummyFav.size).thenReturn(2)
        val favorites = MutableLiveData<PagedList<FavoriteEntity>>()
        favorites.value = dummyFav

        `when`(repos.getFavoritesFromMediaType(MediaType.MOVIE)).thenReturn(favorites)
        val favoriteEntities = viewModel.getFavoritesByMediaType(MediaType.MOVIE).value
        verify(repos).getFavoritesFromMediaType(MediaType.MOVIE)
        assertNotNull(favoriteEntities)
        assertEquals(2, favoriteEntities.size)

        viewModel.getFavoritesByMediaType(MediaType.MOVIE).observeForever(observer)
        verify(observer).onChanged(dummyFav)
    }

    @Test
    fun getFavoritesByMediaType_Tv_shouldReturnFavoritesList() {
        val dummyFav = pagedList
        `when`(dummyFav.size).thenReturn(2)
        val favorites = MutableLiveData<PagedList<FavoriteEntity>>()
        favorites.value = dummyFav

        `when`(repos.getFavoritesFromMediaType(MediaType.TVSHOW)).thenReturn(favorites)
        val favoriteEntities = viewModel.getFavoritesByMediaType(MediaType.TVSHOW).value
        verify(repos).getFavoritesFromMediaType(MediaType.TVSHOW)
        assertNotNull(favoriteEntities)
        assertEquals(2, favoriteEntities.size)

        viewModel.getFavoritesByMediaType(MediaType.TVSHOW).observeForever(observer)
        verify(observer).onChanged(dummyFav)
    }

    @Test
    fun setItemCount_shouldSetItemCount() {
        val dummyItemCount = 5
        val itemCount = MutableLiveData<Int>()
        itemCount.value = dummyItemCount

        `when`(repos.getDbItemCount()).thenReturn(itemCount)
        val countRes = viewModel.getItemCount().value
        verify(repos).getDbItemCount()
        assertNotNull(countRes)
        assertEquals(dummyItemCount, countRes)

        viewModel.getItemCount().observeForever(intObserver)
        verify(intObserver).onChanged(dummyItemCount)
    }
}