package com.nixstudio.moviemax.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import com.nixstudio.moviemax.data.entities.MovieEntity
import com.nixstudio.moviemax.data.entities.TvShowsEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemDetailViewModel(private val repo: MovieMaxRepository) : ViewModel() {

    private var _isBackdropLoading = MutableLiveData<Boolean>()
    private var _isPosterLoading = MutableLiveData<Boolean>()
    private var isUserExist = LiveEvent<Boolean>()

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

    fun addFavorite(
        movie: DiscoverMovieResultsItem? = null,
        tvShows: DiscoverTvResultsItem? = null
    ) {
        var movieEntity: MovieEntity? = null
        var tvShowsEntity: TvShowsEntity? = null

        if (movie != null) {
            movieEntity = MovieEntity(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath
            )
        } else if (tvShows != null) {
            tvShowsEntity = TvShowsEntity(
                id = tvShows.id,
                name = tvShows.name,
                posterPath = tvShows.posterPath
            )
        }

        viewModelScope.launch(Dispatchers.Default) {
            repo.addFavorite(movieEntity, tvShowsEntity)
        }
    }

    fun removeFavorite(
        movie: DiscoverMovieResultsItem? = null,
        tvShows: DiscoverTvResultsItem? = null
    ) {
        var movieEntity: MovieEntity? = null
        var tvShowsEntity: TvShowsEntity? = null

        if (movie != null) {
            movieEntity = MovieEntity(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath
            )
        } else if (tvShows != null) {
            tvShowsEntity = TvShowsEntity(
                id = tvShows.id,
                name = tvShows.name,
                posterPath = tvShows.posterPath
            )
        }

        viewModelScope.launch(Dispatchers.Default) {
            repo.removeFavorite(movieEntity, tvShowsEntity)
        }
    }

    fun checkIfProfileFavorited(id: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            val isExist = withContext(Dispatchers.Default) {
                try {
                    val count = repo.checkIfFavoriteExist(id)

                    Log.d("count", count.toString())

                    if (count > 0) {
                        Log.d("return", true.toString())
                        return@withContext (true)
                    } else {
                        Log.d("return", false.toString())
                        return@withContext (false)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            } as Boolean

            isUserExist.postValue(isExist)
        }
    }

    fun checkIsFavorited(): LiveData<Boolean> = isUserExist
}