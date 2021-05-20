package com.nixstudio.moviemax.views.detail

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.data.utils.credits.CastItem
import com.nixstudio.moviemax.data.utils.reviews.ReviewsItem
import com.nixstudio.moviemax.databinding.ItemDetailFragmentBinding
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel
import com.nixstudio.moviemax.views.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailFragment : Fragment() {

    private var _binding: ItemDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private var isFavorited = false
    private val viewModel by viewModel<ItemDetailViewModel>()

    private lateinit var imgPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvYear: TextView
    private lateinit var tvPlaytimeSeasonTitle: TextView
    private lateinit var tvPlaytimeSeason: TextView
    private lateinit var tvOverview: TextView
    private lateinit var imgBackdrop: ImageView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var shimmer: Shimmer
    private lateinit var shimmerDrawable: ShimmerDrawable
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var tvRating: TextView
    private lateinit var castAdapter: CastAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private var currentTvShows: DiscoverTvResultsItem? = null
    private var currentMovie: DiscoverMovieResultsItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemDetailFragmentBinding.inflate(inflater, container, false)

        lifecycleScope.launchWhenCreated {
            setHasOptionsMenu(true)

            imgPoster = binding.imgPosterDetail
            imgBackdrop = binding.imgBackdrop
            tvTitle = binding.itemTitle
            tvGenre = binding.tvGenre
            tvYear = binding.tvYear
            tvPlaytimeSeasonTitle = binding.tvPlaytimeSeasonTitle
            tvPlaytimeSeason = binding.tvPlaytimeSeason
            tvOverview = binding.tvOverview
            collapsingToolbarLayout = binding.detailCollapsingToolbar
            appBarLayout = binding.detailAppbar
            coordinatorLayout = binding.root
            tvRating = binding.tvRating

            castAdapter = CastAdapter()
            reviewAdapter = ReviewAdapter()

            viewModel.setPosterLoadingState(true)
            viewModel.setBackdropLoadingState(true)

            binding.rvCast.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = castAdapter
                setHasFixedSize(true)
            }

            binding.rvReview.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = reviewAdapter
                setHasFixedSize(true)
            }

            val args: ItemDetailFragmentArgs by navArgs()
            currentMovie = args.movieEntity
            currentTvShows = args.tvShowsEntity

            shimmer =
                Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                    .setDuration(1000) // how long the shimmering animation takes to do one full sweep
                    .setBaseAlpha(0.7f) //the alpha of the underlying children
                    .setHighlightAlpha(0.6f) // the shimmer alpha amount
                    .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                    .setAutoStart(true)
                    .build()

            // This is the placeholder for the imageView
            shimmerDrawable = ShimmerDrawable().apply {
                setShimmer(shimmer)
            }

            if (currentMovie != null) {
                val id = currentMovie?.id
                if (id != null) {
                    viewModel.getCurrentMovie(id).observe(viewLifecycleOwner, { movie ->
                        setMovieData(movie)
                    })
                }
            } else if (currentTvShows != null) {
                val id = currentTvShows?.id
                if (id != null) {
                    viewModel.getCurrentTvShows(id).observe(viewLifecycleOwner, { tvShows ->
                        setTvShows(tvShows)
                    })
                }
            }
        }

        EspressoIdlingResource.increment()
        viewModel.getLoadingState().observe(viewLifecycleOwner, { loadingState ->
            if (!loadingState) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    binding.detailShimmer.visibility = View.GONE
                    binding.itemDetails.visibility = View.VISIBLE
                    binding.detailAppbar.visibility = View.VISIBLE
                }, 500)
            }
        })

        val currentActivity = activity as MainActivity
        val toolbar = binding.detailToolbar
        currentActivity.setSupportActionBar(toolbar)
        currentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    private fun ImageView.loadImage(url: String?, type: String) {
        var width = 500
        var height = 750

        if (type == "backdrop") {
            width = 1920
            height = 1080
        }

        Glide.with(requireActivity())
            .load(url)
            .apply(
                RequestOptions().override(width, height).placeholder(shimmerDrawable)
                    .error(R.drawable.ic_broken_image_black)
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewModel.stopLoading()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (type == "poster") {
                        viewModel.setPosterLoadingState(false)
                    } else {
                        viewModel.setBackdropLoadingState(false)
                    }

                    return false
                }
            })
            .into(this)
    }

    private fun setMovieData(movie: MovieEntity) {
        var posterUrl: String? = null
        var backdropUrl: String? = null

        if (movie.posterPath != null) {
            posterUrl = "https://image.tmdb.org/t/p/w342${movie.posterPath}"
        }

        if (movie.backdropPath != null) {
            backdropUrl = "https://image.tmdb.org/t/p/w780${movie.backdropPath}"
        }

        imgBackdrop.loadImage(backdropUrl, "backdrop")
        imgPoster.loadImage(posterUrl, "poster")

        tvTitle.text = movie.title
        if (!movie.genres.isNullOrEmpty()) {
            tvGenre.text = movie.genres[0]?.name
        } else {
            tvGenre.text = resources.getString(R.string.not_set)
        }

        if (movie.credits?.cast.isNullOrEmpty()) {
            binding.rvCast.visibility = View.GONE
            binding.tvNoCastInfo.visibility = View.VISIBLE
        } else {
            castAdapter.setCasts(movie.credits?.cast as List<CastItem>?)
        }

        if (movie.reviews?.results.isNullOrEmpty()) {
            binding.rvReview.visibility = View.GONE
            binding.tvNoReviews.visibility = View.VISIBLE
        } else {
            reviewAdapter.setReviews(movie.reviews?.results as List<ReviewsItem>?)
        }

        collapsingToolbarLayout.title = movie.title
        tvYear.text = movie.releaseDate
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.playtime)
        tvPlaytimeSeason.text = resources.getString(R.string.minutes, movie.runtime.toString())
        tvOverview.text = movie.overview
        tvRating.text = resources.getString(R.string.rating_value, movie.voteAverage.toString())
    }

    private fun setTvShows(tvShows: TvShowsEntity) {
        var posterUrl: String? = null
        var backdropUrl: String? = null

        if (tvShows.posterPath != null) {
            posterUrl = "https://image.tmdb.org/t/p/w342${tvShows.posterPath}"
        }

        if (tvShows.backdropPath != null) {
            backdropUrl = "https://image.tmdb.org/t/p/w780${tvShows.backdropPath}"
        }

        imgBackdrop.loadImage(backdropUrl, "backdrop")
        imgPoster.loadImage(posterUrl, "poster")

        if (tvShows.credits?.cast.isNullOrEmpty()) {
            binding.rvCast.visibility = View.GONE
            binding.tvNoCastInfo.visibility = View.VISIBLE
        } else {
            castAdapter.setCasts(tvShows.credits?.cast as List<CastItem>?)
        }

        if (tvShows.reviews?.results.isNullOrEmpty()) {
            binding.rvReview.visibility = View.GONE
            binding.tvNoReviews.visibility = View.VISIBLE
        } else {
            reviewAdapter.setReviews(tvShows.reviews?.results as List<ReviewsItem>?)
        }

        collapsingToolbarLayout.title = tvShows.name
        tvTitle.text = tvShows.name
        tvGenre.text = tvShows.genres?.get(0)?.name
        tvYear.text = tvShows.firstAirDate
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.season)
        tvPlaytimeSeason.text = tvShows.numberOfSeasons.toString()
        tvOverview.text = tvShows.overview
        tvRating.text = resources.getString(R.string.rating_value, tvShows.voteAverage.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
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

        super.onCreateOptionsMenu(menu, inflater)
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
                        activity,
                        resources.getString(R.string.fav_add_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    item.setIcon(R.drawable.ic_favorite_red)
                } else {
                    viewModel.removeFavorite(currentMovie, currentTvShows)
                    Toast.makeText(
                        activity,
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}