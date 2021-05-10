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
import com.nixstudio.moviemax.data.sources.remote.DiscoverMovieResultsItem

class HomeMovieAdapter : RecyclerView.Adapter<HomeMovieAdapter.MovieViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listMovie = ArrayList<DiscoverMovieResultsItem>()

    interface OnItemClickCallback {
        fun onItemClicked(data: DiscoverMovieResultsItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<DiscoverMovieResultsItem>?) {
        if (movies == null) return

        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemListMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DiscoverMovieResultsItem) {
            binding.tvTitle.text = movie.title

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

            val url = "https://image.tmdb.org/t/p/original${movie.posterPath}"
            Glide.with(binding.imgPoster.context)
                .load(url)
                .apply(RequestOptions().override(400, 600).placeholder(shimmerDrawable).error(R.drawable.ic_broken_image_black))
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemListMainBinding =
            ItemListMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemListMainBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMovie[holder.absoluteAdapterPosition])
        }

        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size
}