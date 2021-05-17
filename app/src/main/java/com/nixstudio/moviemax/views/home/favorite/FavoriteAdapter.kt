package com.nixstudio.moviemax.views.home.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.entities.FavoriteEntity
import com.nixstudio.moviemax.databinding.ItemListFavoriteBinding
import java.util.*

class FavoriteAdapter :
    PagedListAdapter<FavoriteEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: FavoriteEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavoriteViewHolder(private val binding: ItemListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FavoriteEntity) {
            binding.contentTitle.text = data.title
            binding.contentType.text = data.mediaType.uppercase(Locale.getDefault())

            val shimmer =
                Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
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
                .apply(
                    RequestOptions().override(400, 600).placeholder(shimmerDrawable)
                        .error(R.drawable.ic_broken_image_black)
                )
                .into(binding.imgPosterSearch)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemListFavoriteBinding =
            ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemListFavoriteBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)

        if (favorite != null) {
            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(favorite)
            }
            holder.bind(favorite)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem.itemId == newItem.itemId
            }

            override fun areContentsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}