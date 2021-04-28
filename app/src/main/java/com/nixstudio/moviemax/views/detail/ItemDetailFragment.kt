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
    private lateinit var tvPlaytimeSeasonTitle: TextView
    private lateinit var tvPlaytimeSeason: TextView
    private lateinit var tvOverview: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemDetailFragmentBinding.inflate(inflater, container, false)

        imgPoster = binding.imgPosterDetail
        tvTitle = binding.itemTitle
        tvGenre = binding.tvGenre
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
        Glide.with(requireActivity())
            .load(movie.moviePoster)
            .apply(RequestOptions().error(R.drawable.ic_broken_image_black))
            .into(binding.imgPosterDetail)

        tvTitle.text = movie.movieTitle
        tvGenre.text = movie.genre
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.playtime)
        tvPlaytimeSeason.text = movie.playtime
        tvOverview.text = movie.overview
    }

    private fun setTvShows(tvShows: TvShowsEntity) {
        Glide.with(requireActivity())
            .load(tvShows.tvPoster)
            .apply(RequestOptions().error(R.drawable.ic_broken_image_black))
            .into(binding.imgPosterDetail)

        tvTitle.text = tvShows.tvTitle
        tvGenre.text = tvShows.genre
        tvPlaytimeSeasonTitle.text = resources.getString(R.string.season)
        tvPlaytimeSeason.text = tvShows.season.toString()
        tvOverview.text = tvShows.overview
    }

}