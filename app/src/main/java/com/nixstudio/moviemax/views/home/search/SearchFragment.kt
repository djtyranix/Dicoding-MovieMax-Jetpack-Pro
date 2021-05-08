package com.nixstudio.moviemax.views.home.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.SearchFragmentBinding
import com.nixstudio.moviemax.models.CombinedResultEntity
import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.models.sources.remote.DiscoverTvResultsItem
import com.nixstudio.moviemax.viewmodels.SearchViewModel
import com.nixstudio.moviemax.views.home.HomeActivity
import com.nixstudio.moviemax.views.home.HomeFragmentDirections
import com.nixstudio.moviemax.views.home.HomeTrendingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var viewAdapter: SearchResultAdapter
    private val args: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)

        viewAdapter = SearchResultAdapter()
        viewAdapter.notifyDataSetChanged()

        binding.rvSearchResult.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setSearchResultFromString(args.query)

        val curActivity = activity as HomeActivity
        curActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        curActivity.setActionBarTitle(resources.getString(R.string.search_result_header, args.query))

        viewModel.getSearchResults().observe(viewLifecycleOwner, { item ->
            if (!item.isNullOrEmpty()) {
                viewAdapter.setSearchResult(item)
                binding.rvSearchResult.visibility = View.VISIBLE
                binding.rvSearchShimmer.visibility = View.GONE
            } else {
                binding.rvSearchShimmer.visibility = View.GONE
                binding.rvSearchResult.visibility = View.GONE
                binding.emptySearchPlaceholder.visibility = View.VISIBLE
                binding.emptySearchInfo.visibility = View.VISIBLE
            }
        })

        viewAdapter.setOnItemClickCallback(object: SearchResultAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CombinedResultEntity) {
                if (data.mediaType == "movie") {
                    val movie = DiscoverMovieResultsItem(
                        overview = data.overview,
                        title = data.title,
                        genreIds = data.genreIds,
                        originalLanguage = data.originalLanguage,
                        originalTitle = data.originalTitle,
                        video = data.video,
                        posterPath = data.posterPath,
                        backdropPath = data.backdropPath,
                        releaseDate = data.releaseDate,
                        popularity = data.popularity,
                        voteAverage = data.voteAverage,
                        id = data.id,
                        adult = data.adult,
                        voteCount = data.voteCount
                    )

                    showMovieDetail(movie)
                } else {
                    val tvShow = DiscoverTvResultsItem(
                        overview = data.overview,
                        genreIds = data.genreIds,
                        originalLanguage = data.originalLanguage,
                        posterPath = data.posterPath,
                        backdropPath = data.backdropPath,
                        popularity = data.popularity,
                        voteAverage = data.voteAverage,
                        id = data.id,
                        voteCount = data.voteCount,
                        firstAirDate = data.firstAirDate,
                        originCountry = data.originCountry,
                        originalName = data.originalName,
                        name = data.name
                    )

                    showTvDetail(tvShow)
                }
            }
        })
    }

    private fun showMovieDetail(data: DiscoverMovieResultsItem) {
        val toDetailItemActivity =
            SearchFragmentDirections.actionSearchFragmentToItemDetailActivity(data, null)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }

    private fun showTvDetail(data: DiscoverTvResultsItem) {
        val toDetailItemActivity =
            SearchFragmentDirections.actionSearchFragmentToItemDetailActivity(null, data)
        view?.findNavController()?.navigate(toDetailItemActivity)
    }
}