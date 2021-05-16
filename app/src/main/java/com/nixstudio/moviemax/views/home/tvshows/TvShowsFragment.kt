package com.nixstudio.moviemax.views.home.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.databinding.TvShowsFragmentBinding
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.viewmodels.TvShowsViewModel
import com.nixstudio.moviemax.views.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private var _binding: TvShowsFragmentBinding? = null
    val binding get() = _binding!!
    private val viewModel by viewModel<TvShowsViewModel>()
    lateinit var viewAdapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvShowsFragmentBinding.inflate(inflater, container, false)

        viewAdapter = TvShowsAdapter()
        viewAdapter.notifyDataSetChanged()

        binding.rvAllTvShows.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        val currentActivity = activity as HomeActivity
        val toolbar = binding.homeToolbar.toolbarHome
        currentActivity.setSupportActionBar(toolbar)
        currentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        currentActivity.setActionBarTitle("TV Shows")

        EspressoIdlingResource.increment()
        viewModel.getTvShows().observe(viewLifecycleOwner, { tvItem ->
            if (!tvItem.isNullOrEmpty()) {
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement()
                }
                viewAdapter.setTv(tvItem)
            }
        })

        viewAdapter.setOnItemClickCallback(object : TvShowsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DiscoverTvResultsItem) {
                showMovieDetail(data)
            }
        })

        return binding.root
    }

    private fun showMovieDetail(data: DiscoverTvResultsItem) {
        val toDetailItemActivity =
            TvShowsFragmentDirections.actionTvShowsFragmentToItemDetailActivity(null, data)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}