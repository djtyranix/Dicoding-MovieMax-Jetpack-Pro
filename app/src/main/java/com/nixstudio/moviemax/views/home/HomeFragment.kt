package com.nixstudio.moviemax.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.FragmentHomeBinding
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.viewmodels.HomeViewModel

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    lateinit var movieViewAdapter: HomeMovieAdapter
    lateinit var tvViewAdapter: HomeTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        movieViewAdapter = HomeMovieAdapter()
        tvViewAdapter = HomeTvAdapter()
        movieViewAdapter.notifyDataSetChanged()
        tvViewAdapter.notifyDataSetChanged()

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieViewAdapter
            setHasFixedSize(true)
        }

        binding.rvTvshows.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvViewAdapter
            setHasFixedSize(true)
        }

        binding.seeAllMovies.setOnClickListener(this)
        binding.seeAllTv.setOnClickListener(this)
        binding.tvMovieCount.setOnClickListener(this)
        binding.tvMovieCountSubtitle.setOnClickListener(this)
        binding.tvShowsCount.setOnClickListener(this)
        binding.tvShowsCountSubtitle.setOnClickListener(this)

        val curActivity = activity as HomeActivity
        curActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        curActivity.setActionBarTitle(resources.getString(R.string.app_name))

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setMovies()
        viewModel.setTvShows()

        viewModel.getMovies().observe(viewLifecycleOwner, { movieItem ->
            if (movieItem != null) {
                movieViewAdapter.setMovies(movieItem)
            }
        })

        viewModel.getTvShows().observe(viewLifecycleOwner, { tvItem ->
            if (tvItem != null) {
                tvViewAdapter.setTv(tvItem)
            }
        })

        movieViewAdapter.setOnItemClickCallback(object : HomeMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                showMovieDetail(data)
            }
        })

        tvViewAdapter.setOnItemClickCallback(object : HomeTvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TvShowsEntity) {
                showTvDetail(data)
            }
        })
    }

    private fun showMovieDetail(data: MovieEntity) {
        val toDetailItemActivity =
            HomeFragmentDirections.actionHomeFragmentToItemDetailActivity(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }

    private fun showTvDetail(data: TvShowsEntity) {
        val toDetailItemActivity =
            HomeFragmentDirections.actionHomeFragmentToItemDetailActivity(null, data)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.see_all_movies, R.id.tv_movie_count, R.id.tv_movie_count_subtitle -> {
                val toAllMovie = HomeFragmentDirections.actionHomeFragmentToMovieFragment()
                v.findNavController().navigate(toAllMovie)
            }

            R.id.see_all_tv, R.id.tv_shows_count, R.id.tv_shows_count_subtitle -> {
                val toAllTv = HomeFragmentDirections.actionHomeFragmentToTvShowsFragment()
                v.findNavController().navigate(toAllTv)
            }
        }
    }

    fun setNavigation(selectedDest: Int) {
        if (selectedDest == R.id.setting_menu) {
            val toSettingActivity = HomeFragmentDirections.actionHomeFragmentToSettingsActivity()
            view?.findNavController()?.navigate(toSettingActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}