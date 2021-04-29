package com.nixstudio.moviemax.views.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_detail_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)

                if (currentMovie != null) {
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentMovie?.movieTitle)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "${currentMovie?.movieTitle}\n\nOverview: ${currentMovie?.overview}")
                } else if (currentTvShows != null) {
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentTvShows?.tvTitle)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "${currentTvShows?.tvTitle}\n\nOverview: ${currentTvShows?.overview}")
                }

                shareIntent.type = "text/plain"
                startActivity(shareIntent)
                return true
            }

            else -> return true
        }
    }
}