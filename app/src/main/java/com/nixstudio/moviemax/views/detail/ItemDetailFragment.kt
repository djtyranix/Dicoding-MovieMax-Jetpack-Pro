package com.nixstudio.moviemax.views.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemDetailFragmentBinding
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel

class ItemDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ItemDetailFragment()
    }

    private var _binding: ItemDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ItemDetailViewModel by activityViewModels()

    private lateinit var imgPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvYear: TextView
    private lateinit var tvPlaytimeSeasonTitle: TextView
    private lateinit var tvPlaytimeSeason: TextView
    private lateinit var tvOverview: TextView
    private lateinit var imgBackdrop: ImageView

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

        if (viewModel.getMode() == 0) {
            viewModel.getCurrentMovie().observe(viewLifecycleOwner, { movie ->
                setMovieData(movie)
            })
        } else if (viewModel.getMode() == 1) {
            viewModel.getCurrentTvShows().observe(viewLifecycleOwner, { tvShows ->
                setTvShows(tvShows)
            })
        }

        return binding.root
    }

    private fun setMovieData(movie: MovieEntity) {
        val posterUrl = "https://image.tmdb.org/t/p/w342${movie.posterPath}"

        Glide.with(requireActivity())
            .load(posterUrl)
            .apply(RequestOptions().error(R.drawable.ic_broken_image_black))
            .into(imgPoster)

        if (movie.backdropPath != null) {
            val backdropUrl = "https://image.tmdb.org/t/p/w780${movie.backdropPath}"
            Glide.with(requireActivity())
                .load(backdropUrl)
                .apply(RequestOptions().error(R.drawable.ic_broken_image_black))
                .into(imgBackdrop)
        }

        tvTitle.text = movie.title
        tvGenre.text = movie.genres?.get(0)?.name
        tvYear.text = movie.releaseDate
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.playtime)
        tvPlaytimeSeason.text = resources.getString(R.string.minutes, movie.runtime.toString())
        tvOverview.text = movie.overview
    }

    private fun setTvShows(tvShows: TvShowsEntity) {
        val posterUrl = "https://image.tmdb.org/t/p/w342${tvShows.posterPath}"

        Glide.with(requireActivity())
            .load(posterUrl)
            .apply(RequestOptions().error(R.drawable.ic_broken_image_black))
            .into(imgPoster)

        if (tvShows.backdropPath != null) {
            val backdropUrl = "https://image.tmdb.org/t/p/w780${tvShows.backdropPath}"
            Glide.with(requireActivity())
                .load(backdropUrl)
                .apply(RequestOptions().error(R.drawable.ic_broken_image_black))
                .into(imgBackdrop)
        }

        tvTitle.text = tvShows.name
        tvGenre.text = tvShows.genres?.get(0)?.name
        tvYear.text = tvShows.firstAirDate
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.season)
        tvPlaytimeSeason.text = tvShows.numberOfSeasons.toString()
        tvOverview.text = tvShows.overview
    }

}