package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.CombinedResultEntity
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import com.nixstudio.moviemax.models.sources.remote.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    private val _listSearch = MutableLiveData<List<CombinedResultEntity>>()

    fun setSearchResultFromString(string: String) {
        val searchResponse = repo.searchByString(string)

        searchResponse.enqueue(object: Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.results as ArrayList<CombinedResultEntity>
                    _listSearch.postValue(result.filter { it.mediaType == "movie" || it.mediaType == "tv" })
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getSearchResults(): LiveData<List<CombinedResultEntity>> = _listSearch
}