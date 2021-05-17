package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.utils.MediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(private val repo: MovieMaxRepository) : ViewModel() {

    fun getFavorites() = repo.getAllFavorites()

    fun getFavoritesByMediaType(mediaType: MediaType) = repo.getFavoritesFromMediaType(mediaType)

    fun getItemCount(): LiveData<Int> = repo.getDbItemCount()
}