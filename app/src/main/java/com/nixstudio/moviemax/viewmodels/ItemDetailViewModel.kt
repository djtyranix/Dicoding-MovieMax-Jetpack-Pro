package com.nixstudio.moviemax.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.models.sources.MovieMaxRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDetailViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    private val currentMovie = MutableLiveData<MovieEntity>()
    private val currentTvShows = MutableLiveData<TvShowsEntity>()
//    0 = Movie, 1 = TvShows
    private var _mode: Int = 0
    private var _isBackdropLoading = MutableLiveData<Boolean>()
    private var _isPosterLoading = MutableLiveData<Boolean>()

    fun setCurrentMovie(id: Long) {
        setMode(0)
        val movieResponse = repo.getMovieById(id)

        movieResponse.enqueue(object: Callback<MovieEntity> {
            override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                if (response.isSuccessful) {
                    currentMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setCurrentTvShows(id: Long) {
        setMode(1)
        val tvResponse = repo.getTvShowsById(id)

        tvResponse.enqueue(object: Callback<TvShowsEntity> {
            override fun onResponse(call: Call<TvShowsEntity>, response: Response<TvShowsEntity>) {
                if (response.isSuccessful) {
                    currentTvShows.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TvShowsEntity>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun setMode(mode: Int) {
        _mode = mode
    }

    fun setBackdropLoadingState(state: Boolean) {
        _isBackdropLoading.postValue(state)
    }

    fun setPosterLoadingState(state: Boolean) {
        _isPosterLoading.postValue(state)
    }

    fun stopLoading() {
        _isBackdropLoading.postValue(false)
        _isPosterLoading.postValue(false)
    }

    fun getCurrentMovie(): LiveData<MovieEntity> = currentMovie

    fun getCurrentTvShows(): LiveData<TvShowsEntity> = currentTvShows

    fun getMode(): Int = _mode

    fun getLoadingState(): LiveData<Boolean> {
        return MediatorLiveData<Boolean>().apply {
            var isBackdropLoading: Boolean? = null
            var isPosterLoading: Boolean? = null

            fun update() {
                val localIsBackgroundLoading = isBackdropLoading
                val localIsPosterLoading = isPosterLoading
                if (localIsBackgroundLoading == localIsPosterLoading) {
                    this.value = localIsBackgroundLoading
                }
            }

            addSource(_isPosterLoading) {
                isPosterLoading = it
                update()
            }

            addSource(_isBackdropLoading) {
                isBackdropLoading = it
                update()
            }
        }
    }
}