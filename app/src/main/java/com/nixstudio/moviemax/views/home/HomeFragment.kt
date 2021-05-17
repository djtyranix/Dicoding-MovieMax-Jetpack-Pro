package com.nixstudio.moviemax.views.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.databinding.FragmentHomeBinding
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    private val viewModel by viewModel<HomeViewModel>()
    lateinit var trendingViewAdapter: HomeTrendingAdapter
    lateinit var movieViewAdapter: HomeMovieAdapter
    lateinit var tvViewAdapter: HomeTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        trendingViewAdapter = HomeTrendingAdapter()
        movieViewAdapter = HomeMovieAdapter()
        tvViewAdapter = HomeTvAdapter()
        trendingViewAdapter.notifyDataSetChanged()
        movieViewAdapter.notifyDataSetChanged()
        tvViewAdapter.notifyDataSetChanged()

        binding.rvTrending.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingViewAdapter
            setHasFixedSize(true)
        }

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

        val currentActivity = activity as HomeActivity
        val toolbar = binding.homeToolbar.toolbarHome
        currentActivity.setSupportActionBar(toolbar)
        currentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        currentActivity.setActionBarTitle(" ")

        val searchManager =
            (activity as HomeActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.svSearchItem

        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as HomeActivity).componentName))
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()

                if (query != null && query != "" && query != " ") {
                    val toSearchFragment =
                        HomeFragmentDirections.actionHomeFragmentToSearchFragment(query)
                    view?.findNavController()?.navigate(toSearchFragment)
                } else {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.query_empty_warning),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                return true
            }
        })

        searchView.setOnCloseListener {
            Log.d("Closed", "Closed")
            true
        }

        EspressoIdlingResource.increment()
        EspressoIdlingResource.increment()
        EspressoIdlingResource.increment()

        viewModel.getTrending().observe(viewLifecycleOwner, { item ->
            if (!item.isNullOrEmpty()) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }
                trendingViewAdapter.setTrendingData(item.take(7))
                binding.rvTrending.visibility = View.VISIBLE
                binding.rvTrendingShimmer.visibility = View.GONE
            }
        })

        viewModel.getMovies().observe(viewLifecycleOwner, { movieItem ->
            if (!movieItem.isNullOrEmpty()) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }
                movieViewAdapter.setMovies(movieItem.take(7))
                binding.rvMovie.visibility = View.VISIBLE
                binding.rvMovieShimmer.visibility = View.GONE
            }
        })

        viewModel.getTvShows().observe(viewLifecycleOwner, { tvItem ->
            if (!tvItem.isNullOrEmpty()) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }
                tvViewAdapter.setTv(tvItem.take(7))
                binding.rvTvshows.visibility = View.VISIBLE
                binding.rvTvShimmer.visibility = View.GONE
            }
        })

        trendingViewAdapter.setOnItemClickCallback(object :
            HomeTrendingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CombinedResultEntity) {
                if (data.mediaType == "movie") {
                    val movie = DiscoverMovieResultsItem(
                        overview = data.overview,
                        title = data.title,
                        posterPath = data.posterPath,
                        backdropPath = data.backdropPath,
                        releaseDate = data.releaseDate,
                        popularity = data.popularity,
                        voteAverage = data.voteAverage,
                        id = data.id,
                        adult = data.adult,
                    )

                    showMovieDetail(movie)
                } else {
                    val tvShow = DiscoverTvResultsItem(
                        overview = data.overview,
                        posterPath = data.posterPath,
                        backdropPath = data.backdropPath,
                        popularity = data.popularity,
                        voteAverage = data.voteAverage,
                        id = data.id,
                        firstAirDate = data.firstAirDate,
                        name = data.name
                    )

                    showTvDetail(tvShow)
                }
            }
        })

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

        return binding.root
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