package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository

class SearchViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getSearchResults(string: String): LiveData<List<CombinedResultEntity>> =
        repo.searchByString(string)
}