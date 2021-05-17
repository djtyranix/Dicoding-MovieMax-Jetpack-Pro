package com.nixstudio.moviemax.data.sources

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.local.LocalDataSource
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.data.sources.remote.RemoteDataSource
import com.nixstudio.moviemax.data.utils.MediaType
import com.nixstudio.moviemax.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

class MovieMaxRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val movieMaxRepository = FakeMovieMaxRepository(remote, local)

    @Test
    fun getDiscoveryMovie() {
        val dummyMovies = DummyData.getDiscoveryMovies()
        val movies = MutableLiveData<List<DiscoverMovieResultsItem>>()
        movies.value = dummyMovies

        `when`(remote.getMoviesFromDiscovery(1)).thenReturn(movies)
        val movieResults = LiveDataTestUtil.getValue(movieMaxRepository.getDiscoveryMovie())
        verify(remote).getMoviesFromDiscovery(1)
        assertNotNull(movieResults)
        assertEquals(1, movieResults.size)
    }

    @Test
    fun getDiscoveryTvShows() {
        val dummyTv = DummyData.getDiscoveryTv()
        val tvShow = MutableLiveData<List<DiscoverTvResultsItem>>()
        tvShow.value = dummyTv

        `when`(remote.getTvShowsFromDiscovery(1)).thenReturn(tvShow)
        val tvResult = LiveDataTestUtil.getValue(movieMaxRepository.getDiscoveryTvShows())
        verify(remote).getTvShowsFromDiscovery(1)
        assertNotNull(tvResult)
        assertEquals(1, tvResult.size)
    }

    @Test
    fun searchByString() {
        val dummySearch = DummyData.getCombinedResult()
        val searchList = MutableLiveData<List<CombinedResultEntity>>()
        searchList.value = dummySearch

        `when`(remote.searchFromString("Test", 1)).thenReturn(searchList)
        val searchRes = LiveDataTestUtil.getValue(movieMaxRepository.searchByString("Test"))
        verify(remote).searchFromString("Test", 1)
        assertNotNull(searchRes)
        assertEquals(2, searchRes.size)
        assertEquals(searchRes[0].title, searchRes[0].title)
        assertEquals(searchRes[1].title, searchRes[1].title)
    }

    @Test
    fun getTrendingToday() {
        val dummyTrending = DummyData.getCombinedResult()
        val trending = MutableLiveData<List<CombinedResultEntity>>()
        trending.value = dummyTrending

        `when`(remote.getTrendingToday()).thenReturn(trending)
        val trendingRes = LiveDataTestUtil.getValue(movieMaxRepository.getTrendingToday())
        verify(remote).getTrendingToday()
        assertNotNull(trendingRes)
        assertEquals(2, trendingRes.size)
        assertEquals(trendingRes[0].title, trendingRes[0].title)
        assertEquals(trendingRes[1].title, trendingRes[1].title)
    }

    @Test
    fun getMovieById() {
        val dummyMovies = DummyData.getMovieEntity()
        val movies = MutableLiveData<MovieEntity>()
        movies.value = dummyMovies

        `when`(remote.getMovieById(42069)).thenReturn(movies)
        val movieResults = LiveDataTestUtil.getValue(movieMaxRepository.getMovieById(42069))
        verify(remote).getMovieById(42069)
        assertNotNull(movieResults)
        kotlin.test.assertEquals(dummyMovies.title, movieResults.title)
    }

    @Test
    fun getTvShowsById() {
        val dummyTv = DummyData.getTvShowsEntity()
        val tvShows = MutableLiveData<TvShowsEntity>()
        tvShows.value = dummyTv

        `when`(remote.getTvShowsById(42069)).thenReturn(tvShows)
        val tvResults = LiveDataTestUtil.getValue(movieMaxRepository.getTvShowsById(42069))
        verify(remote).getTvShowsById(42069)
        assertNotNull(tvResults)
        kotlin.test.assertEquals(dummyTv.name, tvResults.name)
    }

    @Test
    fun getAllFavorites() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(local.getAllFavorites()).thenReturn(dataSourceFactory)
        movieMaxRepository.getAllFavorites()

        val favoriteEntities = PagedListUtil.mockPagedList(DummyData.getFavoriteList())
        verify(local).getAllFavorites()
        assertNotNull(favoriteEntities)
        assertEquals(favoriteEntities.size, DummyData.getFavoriteList().size)
    }

    @Test
    fun getFavoritesFromMediaType_Movie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(local.getAllFromMediaType(MediaType.MOVIE)).thenReturn(dataSourceFactory)
        movieMaxRepository.getFavoritesFromMediaType(MediaType.MOVIE)

        val favoriteEntities = PagedListUtil.mockPagedList(DummyData.getFavoriteList())
        verify(local).getAllFromMediaType(MediaType.MOVIE)
        assertNotNull(favoriteEntities)
        assertEquals(favoriteEntities.size, DummyData.getFavoriteList().size)
    }

    @Test
    fun getFavoritesFromMediaType_TV() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(local.getAllFromMediaType(MediaType.TVSHOW)).thenReturn(dataSourceFactory)
        movieMaxRepository.getFavoritesFromMediaType(MediaType.TVSHOW)

        val favoriteEntities = PagedListUtil.mockPagedList(DummyData.getFavoriteList())
        verify(local).getAllFromMediaType(MediaType.TVSHOW)
        assertNotNull(favoriteEntities)
        assertEquals(favoriteEntities.size, DummyData.getFavoriteList().size)
    }
}