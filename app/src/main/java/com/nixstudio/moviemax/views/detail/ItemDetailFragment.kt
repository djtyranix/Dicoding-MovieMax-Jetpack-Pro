package com.nixstudio.moviemax.views.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemDetailFragmentBinding
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemDetailFragment : Fragment() {

    companion object {
        fun newInstance(currentMovie: DiscoverMovieResultsItem? = null, currentTvShows: DiscoverTvResultsItem? = null): ItemDetailFragment = ItemDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("currentMovie", currentMovie)
                putParcelable("currentTvShows", currentTvShows)
            }
        }
    }

    private var _binding: ItemDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<ItemDetailViewModel>()

    private lateinit var imgPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvYear: TextView
    private lateinit var tvPlaytimeSeasonTitle: TextView
    private lateinit var tvPlaytimeSeason: TextView
    private lateinit var tvOverview: TextView
    private lateinit var imgBackdrop: ImageView

    private lateinit var shimmer: Shimmer
    private lateinit var shimmerDrawable: ShimmerDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemDetailFragmentBinding.inflate(inflater, container, false)

        imgPoster = binding.imgPosterDetail
        imgBackdrop = binding.imgBackdrop
        tvTitle = binding.itemTitle
        tvGenre = binding.tvGenre
        tvYear = binding.tvYear
        tvPlaytimeSeasonTitle = binding.tvPlaytimeSeasonTitle
        tvPlaytimeSeason = binding.tvPlaytimeSeason
        tvOverview = binding.tvOverview

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currentMovie = arguments?.getParcelable<DiscoverMovieResultsItem?>("currentMovie")
        val currentTvShows = arguments?.getParcelable<DiscoverTvResultsItem>("currentTvShows")
        val currentActivity = activity as ItemDetailActivity

        viewModel.setPosterLoadingState(true)
        viewModel.setBackdropLoadingState(true)

        viewModel.getLoadingState().observe(viewLifecycleOwner, { loadingState ->
            if (!loadingState) {
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.detailShimmer.visibility = View.GONE
                    binding.itemDetails.visibility = View.VISIBLE
                }, 650)
            }
        })

        shimmer = Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
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
            val id = currentMovie.id
            if (id != null) {
                viewModel.getCurrentMovie(id).observe(viewLifecycleOwner, { movie ->
                    setMovieData(movie)
                })
            }
            currentActivity.setActionBarTitle(resources.getString(R.string.detail_movie))
        } else if (currentTvShows != null) {
            val id = currentTvShows.id
            if (id != null) {
                viewModel.getCurrentTvShows(id).observe(viewLifecycleOwner, { tvShows ->
                    setTvShows(tvShows)
                })
            }
            currentActivity.setActionBarTitle(resources.getString(R.string.detail_tv))
        }
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

        Glide.with(requireActivity())
            .load(backdropUrl)
            .apply(RequestOptions().override(1920, 1080).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
            .listener(object: RequestListener<Drawable> {
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
                    viewModel.setBackdropLoadingState(false)
                    return false
                }
            })
            .into(imgBackdrop)

        Glide.with(requireActivity())
            .load(posterUrl)
            .apply(RequestOptions().override(500, 750).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
            .listener(object: RequestListener<Drawable> {
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
                    viewModel.setPosterLoadingState(false)
                    return false
                }
            })
            .into(imgPoster)


        tvTitle.text = movie.title
        if (!movie.genres.isNullOrEmpty()) {
            tvGenre.text = movie.genres[0]?.name
        } else {
            tvGenre.text = resources.getString(R.string.not_set)
        }

        tvYear.text = movie.releaseDate
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.playtime)
        tvPlaytimeSeason.text = resources.getString(R.string.minutes, movie.runtime.toString())
        tvOverview.text = movie.overview
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

        Glide.with(requireActivity())
            .load(backdropUrl)
            .apply(RequestOptions().override(1920, 1080).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
            .addListener(object: RequestListener<Drawable> {
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
                    viewModel.setBackdropLoadingState(false)
                    return false
                }
            })
            .into(imgBackdrop)

        Glide.with(requireActivity())
            .load(posterUrl)
            .apply(RequestOptions().override(500, 750).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
            .addListener(object: RequestListener<Drawable> {
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
                    viewModel.setPosterLoadingState(false)
                    return false
                }
            })
            .into(imgPoster)

        tvTitle.text = tvShows.name
        tvGenre.text = tvShows.genres?.get(0)?.name
        tvYear.text = tvShows.firstAirDate
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.season)
        tvPlaytimeSeason.text = tvShows.numberOfSeasons.toString()
        tvOverview.text = tvShows.overview
    }

}