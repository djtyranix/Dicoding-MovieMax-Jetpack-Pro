package com.nixstudio.moviemax.views.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.databinding.MovieFragmentBinding
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.viewmodels.MovieViewModel
import com.nixstudio.moviemax.views.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MovieViewModel>()
    private lateinit var viewAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)

        viewAdapter = MovieAdapter()
        viewAdapter.notifyDataSetChanged()

        binding.rvAllMovie.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        val currentActivity = activity as HomeActivity
        val toolbar = binding.homeToolbar.toolbarHome
        currentActivity.setSupportActionBar(toolbar)
        currentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        currentActivity.setActionBarTitle("Movies")

        EspressoIdlingResource.increment()
        viewModel.getMovies().observe(viewLifecycleOwner, { movieItem ->
            if (!movieItem.isNullOrEmpty()) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }
                viewAdapter.setMovies(movieItem)
            }
        })

        viewAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DiscoverMovieResultsItem) {
                showMovieDetail(data)
            }
        })

        return binding.root
    }

    private fun showMovieDetail(data: DiscoverMovieResultsItem) {
        val toDetailItemActivity =
            MovieFragmentDirections.actionMovieFragmentToItemDetailActivity(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}