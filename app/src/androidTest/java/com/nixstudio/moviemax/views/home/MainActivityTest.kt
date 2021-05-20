package com.nixstudio.moviemax.views.home

import android.content.Context
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.views.MainActivity
import com.nixstudio.moviemax.views.favorite.FavoriteAdapter
import com.nixstudio.moviemax.views.movie.MovieAdapter
import com.nixstudio.moviemax.views.tvshows.TvShowsAdapter
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private lateinit var instrumentalContext: Context

    @Before
    fun setup() {
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext

        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Change view text"
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, true)
            }
        }
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

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("PLAYTIME")))
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

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("PLAYTIME")))
    }

    @Test
    fun usecase2a_testingDetailTvShowsFromHome() {

        onView(withId(R.id.rv_tvshows))
            .perform(scrollTo())

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

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("SEASON")))
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

        onView(withId(R.id.tv_playtime_season_title))
            .check(matches(withText("SEASON")))
    }

    @Test
    fun usecase3_testingDetailTrendingFromHome() {
//        TAKE THE FIRST ITEM
        onView(withId(R.id.rv_trending))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeTrendingAdapter.TrendingViewHolder>(
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
    }

    @Test
    fun usecase4a_testingSearchWithResultToDetail() {
        val dummyQuery = "Avengers"
        onView(withId(R.id.sv_search_item))
            .perform(click())

        onView(withId(R.id.sv_search_item))
            .perform(typeSearchViewText(dummyQuery))



        onView(withId(R.id.rv_search_result))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeTrendingAdapter.TrendingViewHolder>(
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
    }

    @Test
    fun usecase4b_testingSearchWithNoResult() {
        val dummyQuery = "Atatar"
        onView(withId(R.id.sv_search_item))
            .perform(click())

        onView(withId(R.id.sv_search_item))
            .perform(typeSearchViewText(dummyQuery))

        onView(withId(R.id.empty_search_info))
            .check(matches(isDisplayed()))

        onView(withId(R.id.empty_search_placeholder))
            .check(matches(isDisplayed()))
    }

    @Test
    fun usecase5a_testingAddingFavorite() {
        onView(withId(R.id.rv_trending))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeTrendingAdapter.TrendingViewHolder>(
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

        onView(withId(R.id.favorite))
            .perform(click())

        Espresso.pressBack()

        onView(withId(R.id.favorite))
            .perform(click())

        onView(withId(R.id.rv_favorite))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<FavoriteAdapter.FavoriteViewHolder>(
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
    }

    @Test
    fun usecase5b_testingRemovingFavorite() {
        onView(withId(R.id.rv_trending))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeTrendingAdapter.TrendingViewHolder>(
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

        onView(withId(R.id.favorite))
            .perform(click())

        Espresso.pressBack()

        onView(withId(R.id.favorite))
            .perform(click())

        onView(withId(R.id.rv_favorite))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<FavoriteAdapter.FavoriteViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.favorite))
            .perform(click())

        Espresso.pressBack()

        onView(withId(R.id.empty_favorite_info))
            .check(matches(isDisplayed()))

        onView(withId(R.id.empty_favorite_placeholder))
            .check(matches(isDisplayed()))
    }
}