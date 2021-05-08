package com.nixstudio.moviemax.views.home.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemListSearchBinding
import com.nixstudio.moviemax.models.CombinedResultEntity
import java.util.*
import kotlin.collections.ArrayList

class SearchResultAdapter: RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listSearch = ArrayList<CombinedResultEntity>()

    inner class SearchViewHolder(private val binding: ItemListSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CombinedResultEntity) {
            if (data.mediaType == "movie") {
                binding.contentTitle.text = data.title
            } else {
                binding.contentTitle.text = data.name
            }

            binding.contentType.text = data.mediaType?.uppercase(Locale.getDefault())

            val shimmer = Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1000) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.6f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

            // This is the placeholder for the imageView
            val shimmerDrawable = ShimmerDrawable().apply {
                setShimmer(shimmer)
            }

            val url = "https://image.tmdb.org/t/p/w300${data.posterPath}"
            Glide.with(binding.imgPosterSearch.context)
                .load(url)
                .apply(RequestOptions().override(400, 600).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
                .into(binding.imgPosterSearch)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CombinedResultEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setSearchResult(data: List<CombinedResultEntity>?) {
        if (data == null) return

        this.listSearch.clear()
        this.listSearch.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemListSearchBinding =
            ItemListSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemListSearchBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = listSearch[position]

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listSearch[holder.absoluteAdapterPosition])
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int = listSearch.size
}