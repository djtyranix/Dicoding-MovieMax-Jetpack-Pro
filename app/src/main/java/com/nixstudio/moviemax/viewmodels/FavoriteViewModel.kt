package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import com.nixstudio.moviemax.data.utils.MediaType

class FavoriteViewModel(private val repo: MovieMaxRepository) : ViewModel() {

    fun getFavorites() = repo.getAllFavorites()

    fun getFavoritesByMediaType(mediaType: MediaType) = repo.getFavoritesFromMediaType(mediaType)

    fun getItemCount(): LiveData<Int> = repo.getDbItemCount()
}