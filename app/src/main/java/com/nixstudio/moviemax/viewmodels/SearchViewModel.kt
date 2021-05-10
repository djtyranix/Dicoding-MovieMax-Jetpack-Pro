package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixstudio.moviemax.data.entities.CombinedResultEntity
import com.nixstudio.moviemax.data.sources.MovieMaxRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: MovieMaxRepository) : ViewModel() {
    fun getSearchResults(string: String): LiveData<List<CombinedResultEntity>> = repo.searchByString(string)
}