package com.nixstudio.moviemax.views.favorite

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.data.utils.MediaType
import com.nixstudio.moviemax.databinding.FavoriteFragmentBinding
import com.nixstudio.moviemax.utils.EspressoIdlingResource
import com.nixstudio.moviemax.viewmodels.FavoriteViewModel
import com.nixstudio.moviemax.views.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var viewAdapter: FavoriteAdapter
    private var isSpinnerInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)

        lifecycleScope.launchWhenCreated {
            viewAdapter = FavoriteAdapter()
            viewAdapter.notifyDataSetChanged()

            binding.rvFavorite.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = viewAdapter
                setHasFixedSize(true)
            }

            viewAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
                override fun onItemClicked(data: FavoriteEntity) {
                    if (data.mediaType == "movie") {
                        val movie = DiscoverMovieResultsItem(
                            title = data.title,
                            posterPath = data.posterPath,
                            id = data.itemId,
                        )

                        showMovieDetail(movie)
                    } else {
                        val tvShow = DiscoverTvResultsItem(
                            posterPath = data.posterPath,
                            id = data.itemId,
                            name = data.title
                        )

                        showTvDetail(tvShow)
                    }
                }
            })
        }

        EspressoIdlingResource.increment()
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (!isSpinnerInitialized) {
                    isSpinnerInitialized = true
                    viewModel.getFavorites().observe(viewLifecycleOwner, {
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }

                        viewAdapter.submitList(it)
                        checkIsListEmpty(it.size)
                    })
                    return
                }

                when (id) {
                    0L -> {
                        viewModel.getFavorites().observe(viewLifecycleOwner, {
                            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                                EspressoIdlingResource.decrement()
                            }

                            viewAdapter.submitList(it)
                            checkIsListEmpty(it.size)
                        })
                    }
                    1L -> {
                        viewModel.getFavoritesByMediaType(MediaType.MOVIE)
                            .observe(viewLifecycleOwner, {
                                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                                    EspressoIdlingResource.decrement()
                                }

                                viewAdapter.submitList(it)
                                checkIsListEmpty(it.size)
                            })
                    }
                    else -> {
                        viewModel.getFavoritesByMediaType(MediaType.TVSHOW)
                            .observe(viewLifecycleOwner, {
                                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                                    EspressoIdlingResource.decrement()
                                }

                                viewAdapter.submitList(it)
                                checkIsListEmpty(it.size)
                            })
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }

        EspressoIdlingResource.increment()
        viewModel.getItemCount().observe(viewLifecycleOwner, { count ->
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
            checkIsListEmpty(count)
        })

        val currentActivity = activity as MainActivity
        val toolbar = binding.homeToolbar.toolbarHome
        currentActivity.setSupportActionBar(toolbar)
        currentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        currentActivity.setActionBarTitle(resources.getString(R.string.favorite))

        return binding.root
    }

    private fun checkIsListEmpty(count: Int) {
        if (count > 0) {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.rvFavorite.visibility = View.VISIBLE
                binding.view2.visibility = View.VISIBLE
                binding.sortSpinner.visibility = View.VISIBLE
                binding.textView.visibility = View.VISIBLE
                binding.rvFavoriteShimmer.visibility = View.GONE
                binding.emptyFavoritePlaceholder.visibility = View.GONE
                binding.emptyFavoriteInfo.visibility = View.GONE
            }, 500)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.textView.visibility = View.VISIBLE
                binding.view2.visibility = View.VISIBLE
                binding.sortSpinner.visibility = View.VISIBLE
                binding.rvFavoriteShimmer.visibility = View.GONE
                binding.rvFavorite.visibility = View.GONE
                binding.emptyFavoritePlaceholder.visibility = View.VISIBLE
                binding.emptyFavoriteInfo.visibility = View.VISIBLE
            }, 500)
        }
    }

    private fun showMovieDetail(data: DiscoverMovieResultsItem) {
        val toDetailItemActivity =
            FavoriteFragmentDirections.actionFavoriteFragmentToItemDetailFragment(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }

    private fun showTvDetail(data: DiscoverTvResultsItem) {
        val toDetailItemActivity =
            FavoriteFragmentDirections.actionFavoriteFragmentToItemDetailFragment(null, data)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}