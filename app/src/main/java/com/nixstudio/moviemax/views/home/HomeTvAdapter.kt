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
import com.nixstudio.moviemax.data.sources.remote.DiscoverTvResultsItem

class HomeTvAdapter : RecyclerView.Adapter<HomeTvAdapter.TvShowsViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listTv = ArrayList<DiscoverTvResultsItem>()

    interface OnItemClickCallback {
        fun onItemClicked(data: DiscoverTvResultsItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTv(tvShows: List<DiscoverTvResultsItem>?) {
        if (tvShows == null) return

        this.listTv.clear()
        this.listTv.addAll(tvShows)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(private val binding: ItemListMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: DiscoverTvResultsItem) {
            binding.tvTitle.text = tv.name

            val url = "https://image.tmdb.org/t/p/original${tv.posterPath}"
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
            Glide.with(binding.imgPoster.context)
                .load(url)
                .apply(RequestOptions().override(400, 600).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsTvBinding =
            ItemListMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShow = listTv[position]

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTv[holder.absoluteAdapterPosition])
        }

        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTv.size
}