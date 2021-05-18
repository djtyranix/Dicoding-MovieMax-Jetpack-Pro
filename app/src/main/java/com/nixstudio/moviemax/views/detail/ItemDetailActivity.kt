package com.nixstudio.moviemax.views.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailActivity : AppCompatActivity() {

    private var currentMovie: DiscoverMovieResultsItem? = null
    private var currentTvShows: DiscoverTvResultsItem? = null
    private var isFavorited = false
    private val viewModel by viewModel<ItemDetailViewModel>()

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

        if (currentMovie != null) {
            currentMovie?.id?.let { viewModel.checkIfProfileFavorited(it) }
        } else if (currentTvShows != null) {
            currentTvShows?.id?.let { viewModel.checkIfProfileFavorited(it) }
        }

        viewModel.checkIsFavorited().observe(this, { isExist ->
            isFavorited = if (isExist) { //User already favorited
                menu.findItem(R.id.favorite).setIcon(R.drawable.ic_favorite_red)
                true
            } else {
                menu.findItem(R.id.favorite).setIcon(R.drawable.ic_favorite)
                false
            }
        }
        )

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
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
            }
            R.id.favorite -> {
                isFavorited = !isFavorited

                if (isFavorited) { //Add user
                    viewModel.addFavorite(currentMovie, currentTvShows)
                    Toast.makeText(
                        this,
                        resources.getString(R.string.fav_add_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    item.setIcon(R.drawable.ic_favorite_red)
                } else {
                    viewModel.removeFavorite(currentMovie, currentTvShows)
                    Toast.makeText(
                        this,
                        resources.getString(R.string.fav_del_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    item.setIcon(R.drawable.ic_favorite)
                }

                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}