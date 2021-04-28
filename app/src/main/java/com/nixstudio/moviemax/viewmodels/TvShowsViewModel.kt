package com.nixstudio.moviemax.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity
import com.nixstudio.moviemax.utils.DummyData

class TvShowsViewModel : ViewModel() {
    private val listTv = MutableLiveData<List<TvShowsEntity>>()
    private val _listTvList = ArrayList<TvShowsEntity>()

    val listTvList: ArrayList<TvShowsEntity>
        get() = _listTvList

    fun setTvShows() {
        val tvShows = DummyData.generateTvShows()
        listTv.postValue(tvShows)
        _listTvList.addAll(tvShows)
    }

    fun getTvShows(): LiveData<List<TvShowsEntity>> = listTv
}