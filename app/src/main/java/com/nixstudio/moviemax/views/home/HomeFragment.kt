package com.nixstudio.moviemax.views.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.FragmentHomeBinding
import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.models.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    private val viewModel by viewModel<HomeViewModel>()
    lateinit var movieViewAdapter: HomeMovieAdapter
    lateinit var tvViewAdapter: HomeTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.setMovies()
        viewModel.setTvShows()

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

        binding.seeAllMovies.setOnClickListener(this)
        binding.seeAllTv.setOnClickListener(this)

        val curActivity = activity as HomeActivity
        curActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        curActivity.setActionBarTitle(resources.getString(R.string.app_name))

        val searchManager = (activity as HomeActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.svSearchItem

        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as HomeActivity).componentName))
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }
        })

        searchView.setOnCloseListener {
            Log.d("Closed", "Closed")
            true
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieViewAdapter.setOnItemClickCallback(object : HomeMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DiscoverMovieResultsItem) {
                showMovieDetail(data)
            }
        })

        tvViewAdapter.setOnItemClickCallback(object : HomeTvAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DiscoverTvResultsItem) {
                showTvDetail(data)
            }
        })
    }

    private fun showMovieDetail(data: DiscoverMovieResultsItem) {
        val toDetailItemActivity =
            HomeFragmentDirections.actionHomeFragmentToItemDetailActivity(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }

    private fun showTvDetail(data: DiscoverTvResultsItem) {
        val toDetailItemActivity =
            HomeFragmentDirections.actionHomeFragmentToItemDetailActivity(null, data)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.see_all_movies -> {
                val toAllMovie = HomeFragmentDirections.actionHomeFragmentToMovieFragment()
                v.findNavController().navigate(toAllMovie)
            }

            R.id.see_all_tv -> {
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