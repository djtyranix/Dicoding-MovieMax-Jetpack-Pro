package com.nixstudio.moviemax.views.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel

class ItemDetailActivity : AppCompatActivity() {

    private val viewModel: ItemDetailViewModel by viewModels()
    private var currentMovie: MovieEntity? = null
    private var currentTvShows: TvShowsEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail_activity)

        val args: ItemDetailActivityArgs? by navArgs()
        currentMovie = args?.movieEntity
        currentTvShows = args?.tvShowsEntity

        if (currentMovie != null) {
            viewModel.setCurrrentMovie(currentMovie!!)
            setActionBarTitle(resources.getString(R.string.detail_movie))
        } else if (currentTvShows != null) {
            viewModel.setCurrentTvShows(currentTvShows!!)
            setActionBarTitle(resources.getString(R.string.detail_tv))
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ItemDetailFragment.newInstance())
                .commitNow()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}