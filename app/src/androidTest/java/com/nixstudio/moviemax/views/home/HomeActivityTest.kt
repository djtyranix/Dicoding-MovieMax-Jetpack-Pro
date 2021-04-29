package com.nixstudio.moviemax.views.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.utils.DummyData
import com.nixstudio.moviemax.views.home.movie.MovieAdapter
import com.nixstudio.moviemax.views.home.tvshows.TvShowsAdapter
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    private val dummyLatestMovieList = DummyData.generateLatestMovies()
    private val dummyLatestTvShowsList = DummyData.generateLatestTvShows()
    private val dummyMovieList = DummyData.generateMovies()
    private val dummyTvShowsList = DummyData.generateTvShows()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun usecase1a_testingDetailMovieFromHome() {
//        TAKE THE FIRST ITEM
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeMovieAdapter.MovieViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.item_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_year))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.item_title))
            .check(matches(withText(dummyLatestMovieList[0].movieTitle)))

        onView(withId(R.id.tv_year))
            .check(matches(withText(dummyLatestMovieList[0].movieYear.toString())))

        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyLatestMovieList[0].genre)))

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("PLAYTIME")))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(withText(dummyLatestMovieList[0].playtime)))

        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyLatestMovieList[0].overview)))
    }

    @Test
    fun usecase1b_testingDetailMovieFromAllMovies() {
        onView(withId(R.id.see_all_movies))
            .perform(click())

        onView(withId(R.id.rv_all_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.item_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_year))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.item_title))
            .check(matches(withText(dummyMovieList[0].movieTitle)))

        onView(withId(R.id.tv_year))
            .check(matches(withText(dummyMovieList[0].movieYear.toString())))

        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyMovieList[0].genre)))

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("PLAYTIME")))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(withText(dummyMovieList[0].playtime)))

        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyMovieList[0].overview)))
    }

    @Test
    fun usecase1c_testingDetailMovieFromAllMoviesFromCount() {
        onView(withId(R.id.tv_movie_count))
            .perform(click())

        onView(withId(R.id.rv_all_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.item_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_year))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.item_title))
            .check(matches(withText(dummyMovieList[0].movieTitle)))

        onView(withId(R.id.tv_year))
            .check(matches(withText(dummyMovieList[0].movieYear.toString())))

        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyMovieList[0].genre)))

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("PLAYTIME")))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(withText(dummyMovieList[0].playtime)))

        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyMovieList[0].overview)))
    }

    @Test
    fun usecase2a_testingDetailTvShowsFromHome() {
//        TAKE THE FIRST ITEM
        onView(withId(R.id.rv_tvshows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeTvAdapter.TvShowsViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.item_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_year))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.item_title))
            .check(matches(withText(dummyLatestTvShowsList[0].tvTitle)))

        onView(withId(R.id.tv_year))
            .check(matches(withText(dummyLatestTvShowsList[0].tvYear.toString())))

        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyLatestTvShowsList[0].genre)))

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("SEASON")))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(withText(dummyLatestTvShowsList[0].season.toString())))

        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyLatestTvShowsList[0].overview)))
    }

    @Test
    fun usecase2b_testingDetailTvShowsFromAllTvShows() {
        onView(withId(R.id.see_all_tv))
            .perform(click())

        onView(withId(R.id.rv_all_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TvShowsAdapter.TvShowsViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.item_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_year))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.item_title))
            .check(matches(withText(dummyTvShowsList[0].tvTitle)))

        onView(withId(R.id.tv_year))
            .check(matches(withText(dummyTvShowsList[0].tvYear.toString())))

        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyTvShowsList[0].genre)))

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("SEASON")))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(withText(dummyTvShowsList[0].season.toString())))

        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyTvShowsList[0].overview)))
    }

    @Test
    fun usecase2c_testingDetailTvShowsFromAllTvShowsFromCount() {
        onView(withId(R.id.tv_shows_count))
            .perform(click())

        onView(withId(R.id.rv_all_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TvShowsAdapter.TvShowsViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.item_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_year))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))

        onView(withId(R.id.item_title))
            .check(matches(withText(dummyTvShowsList[0].tvTitle)))

        onView(withId(R.id.tv_year))
            .check(matches(withText(dummyTvShowsList[0].tvYear.toString())))

        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyTvShowsList[0].genre)))

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("SEASON")))

        onView(withId(R.id.tv_playtime_season))
            .check(matches(withText(dummyTvShowsList[0].season.toString())))

        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyTvShowsList[0].overview)))
    }
}