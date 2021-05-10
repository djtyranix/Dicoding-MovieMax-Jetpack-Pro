package com.nixstudio.moviemax.utils

import com.nixstudio.moviemax.data.entities.CombinedResultEntity
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
            overview = "Yes",
            title = "This is a Movie Test"
        )
    }

    fun getTvShowsEntity(): TvShowsEntity {
        return TvShowsEntity(
            overview = "Nice",
            name = "This is a TV Test"
        )
    }
}