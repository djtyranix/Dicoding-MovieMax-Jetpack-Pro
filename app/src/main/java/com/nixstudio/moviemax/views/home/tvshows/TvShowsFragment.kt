package com.nixstudio.moviemax.views.home.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nixstudio.moviemax.databinding.TvShowsFragmentBinding
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.viewmodels.TvShowsViewModel

class TvShowsFragment : Fragment() {

    private var _binding: TvShowsFragmentBinding? = null
    val binding get() = _binding!!
    private val viewModel: TvShowsViewModel by viewModels()
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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setTvShows()

        viewModel.getTvShows().observe(viewLifecycleOwner, { tvItem ->
            if (tvItem != null) {
                viewAdapter.setTv(tvItem)
            }
        })

        viewAdapter.setOnItemClickCallback(object : TvShowsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TvShowsEntity) {
                showMovieDetail(data)
            }
        })

        val curActivity = activity as AppCompatActivity
        curActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showMovieDetail(data: TvShowsEntity) {
        val toDetailItemActivity =
            TvShowsFragmentDirections.actionTvShowsFragmentToItemDetailActivity(null, data)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}