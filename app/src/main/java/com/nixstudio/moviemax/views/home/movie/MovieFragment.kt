package com.nixstudio.moviemax.views.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.MovieFragmentBinding
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.viewmodels.MovieViewModel
import com.nixstudio.moviemax.views.home.HomeActivity

class MovieFragment : Fragment() {

    private var _binding: MovieFragmentBinding? = null
    val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModels()
    lateinit var viewAdapter: MovieAdapter

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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setMovies()

        viewModel.getMovies().observe(viewLifecycleOwner, { movieItem ->
            if (movieItem != null) {
                viewAdapter.setMovies(movieItem)
            }
        })

        viewAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                showMovieDetail(data)
            }
        })

        val curActivity = activity as HomeActivity
        curActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        curActivity.setActionBarTitle(resources.getString(R.string.movies))
    }

    private fun showMovieDetail(data: MovieEntity) {
        val toDetailItemActivity =
            MovieFragmentDirections.actionMovieFragmentToItemDetailActivity(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}