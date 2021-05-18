package com.nixstudio.moviemax.utils

import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem

object DummyData {
    fun getDiscoveryMovies(): List<DiscoverMovieResultsItem> {
        val list = ArrayList<DiscoverMovieResultsItem>()

        list.add(
            DiscoverMovieResultsItem(
                overview = "Yes",
                title = "Test Movie",
            )
        )

        return list
    }

    fun getDiscoveryTv(): List<DiscoverTvResultsItem> {
        val list = ArrayList<DiscoverTvResultsItem>()

        list.add(
            DiscoverTvResultsItem(
                overview = "Yes",
                name = "Test TV",
            )
        )

        return list
    }

    fun getCombinedResult(): List<CombinedResultEntity> {
        val list = ArrayList<CombinedResultEntity>()

        list.add(
            CombinedResultEntity(
                overview = "Yes",
                title = "Test 1"
            )
        )

        list.add(
            CombinedResultEntity(
                overview = "Yes",
                name = "Test 2"
            )
        )

        return list
    }

    fun getMovieEntity(): MovieEntity {
        return MovieEntity(
            id = 1,
            overview = "Yes",
            title = "This is a Movie Test"
        )
    }

    fun getTvShowsEntity(): TvShowsEntity {
        return TvShowsEntity(
            id = 1,
            overview = "Nice",
            name = "This is a TV Test"
        )
    }

    fun getFavoriteList(): List<FavoriteEntity> {
        val list = ArrayList<FavoriteEntity>()

        list.add(
            FavoriteEntity(
                itemId = 1,
                title = "Test",
                mediaType = "movie",
                posterPath = null
            )
        )

        list.add(
            FavoriteEntity(
                itemId = 2,
                title = "Test 2",
                mediaType = "tv",
                posterPath = null
            )
        )

        return list
    }
}