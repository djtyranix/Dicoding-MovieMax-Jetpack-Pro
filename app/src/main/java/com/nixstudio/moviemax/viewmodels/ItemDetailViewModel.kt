package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.*
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import kotlinx.coroutines.launch

class ItemDetailViewModel(private val repo: MovieMaxRepository) : ViewModel() {

    private var _isBackdropLoading = MutableLiveData<Boolean>()
    private var _isPosterLoading = MutableLiveData<Boolean>()

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

    fun getCurrentMovie(id: Long): LiveData<MovieEntity> = repo.getMovieById(id)

    fun getCurrentTvShows(id: Long): LiveData<TvShowsEntity> = repo.getTvShowsById(id)

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