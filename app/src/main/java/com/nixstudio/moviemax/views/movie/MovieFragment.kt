package com.nixstudio.moviemax.views.movie

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.databinding.MovieFragmentBinding
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.viewmodels.MovieViewModel
import com.nixstudio.moviemax.views.MainActivity
import kotlinx.coroutines.launch
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

        lifecycleScope.launchWhenCreated {
            viewAdapter = MovieAdapter()
            viewAdapter.notifyDataSetChanged()

            binding.rvAllMovie.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = viewAdapter
                setHasFixedSize(true)
            }

            viewAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DiscoverMovieResultsItem) {
                    showMovieDetail(data)
                }
            })
        }

        EspressoIdlingResource.increment()
        viewModel.getMovies().observe(viewLifecycleOwner, { movieItem ->
            if (!movieItem.isNullOrEmpty()) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }

                lifecycleScope.launch {
                    viewAdapter.setMovies(movieItem)
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    binding.rvAllMovie.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.view2.visibility = View.VISIBLE
                    binding.movieShimmer.visibility = View.GONE
                }, 500)
            }
        })

        val currentActivity = activity as MainActivity
        val toolbar = binding.homeToolbar.toolbarHome
        currentActivity.setSupportActionBar(toolbar)
        currentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        currentActivity.setActionBarTitle("Movies")

        return binding.root
    }

    private fun showMovieDetail(data: DiscoverMovieResultsItem) {
        val toDetailItemActivity =
            MovieFragmentDirections.actionMovieFragmentToItemDetailFragment(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}