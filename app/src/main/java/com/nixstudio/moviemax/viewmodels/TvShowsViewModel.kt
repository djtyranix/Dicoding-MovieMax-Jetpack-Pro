package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import com.nixstudio.moviemax.models.sources.remote.DiscoverTvResponse
import com.nixstudio.moviemax.models.sources.remote.DiscoverTvResultsItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowsViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    private val listTv = MutableLiveData<List<DiscoverTvResultsItem>>()

    fun setTvShows() {
        val discoverTvResponse = repo.getDiscoveryTvShows()

        discoverTvResponse.enqueue(object : Callback<DiscoverTvResponse> {
            override fun onResponse(
                call: Call<DiscoverTvResponse>,
                response: Response<DiscoverTvResponse>
            ) {
                if (response.isSuccessful) {
                    listTv.postValue(response.body()?.results as ArrayList<DiscoverTvResultsItem>)
                }
            }

            override fun onFailure(call: Call<DiscoverTvResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvShows(): LiveData<List<DiscoverTvResultsItem>> = listTv
}