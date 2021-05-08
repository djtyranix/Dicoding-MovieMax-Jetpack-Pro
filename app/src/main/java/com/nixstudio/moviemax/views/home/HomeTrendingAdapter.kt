package com.nixstudio.moviemax.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemListMainBinding
import com.nixstudio.moviemax.models.CombinedResultEntity

class HomeTrendingAdapter: RecyclerView.Adapter<HomeTrendingAdapter.TrendingViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listTrending = ArrayList<CombinedResultEntity>()

    interface OnItemClickCallback {
        fun onItemClicked(data: CombinedResultEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTrendingData(list: List<CombinedResultEntity>?) {
        if (list == null) return

        this.listTrending.clear()
        this.listTrending.addAll(list)
        notifyDataSetChanged()
    }

    inner class TrendingViewHolder(private val binding: ItemListMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CombinedResultEntity) {
            if (item.mediaType == "movie") {
                binding.tvTitle.text = item.title
            } else {
                binding.tvTitle.text = item.name
            }

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

            val url = "https://image.tmdb.org/t/p/original${item.posterPath}"
            Glide.with(binding.imgPoster.context)
                .load(url)
                .apply(RequestOptions().override(400, 600).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val itemListMainBinding =
            ItemListMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(itemListMainBinding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = listTrending[position]

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTrending[holder.absoluteAdapterPosition])
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int = listTrending.size
}