package com.nixstudio.moviemax.views.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.viewmodels.ItemDetailViewModel

class ItemDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ItemDetailFragment()
    }

    private lateinit var viewModel: ItemDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.item_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}