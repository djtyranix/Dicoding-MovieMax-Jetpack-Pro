package com.nixstudio.moviemax.views.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem

class ItemDetailActivity : AppCompatActivity() {

    var currentMovie: DiscoverMovieResultsItem? = null
    var currentTvShows: DiscoverTvResultsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail_activity)

        val args: ItemDetailActivityArgs by navArgs()
        currentMovie = args.movieEntity
        currentTvShows = args.tvShowsEntity

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    ItemDetailFragment.newInstance(currentMovie, currentTvShows)
                )
                .commitNow()
        }
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
        if (item.itemId == R.id.share) {
            val shareIntent = Intent(Intent.ACTION_SEND)

            if (currentMovie != null) {
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentMovie?.title)
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${currentMovie?.title}\n\nOverview: ${currentMovie?.overview}"
                )
            } else if (currentTvShows != null) {
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentTvShows?.name)
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "${currentTvShows?.name}\n\nOverview: ${currentTvShows?.overview}"
                )
            }

            shareIntent.type = "text/plain"
            startActivity(shareIntent)
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}