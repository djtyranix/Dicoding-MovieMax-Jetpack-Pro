package com.nixstudio.moviemax.views.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemListFullBinding
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResultsItem

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
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

    inner class MovieViewHolder(private val binding: ItemListFullBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DiscoverMovieResultsItem) {
            binding.tvTitle.text = movie.title

            val url = "https://image.tmdb.org/t/p/original${movie.posterPath}"
            Glide.with(binding.imgPoster.context)
                .load(url)
                .apply(RequestOptions().override(400, 600).error(R.drawable.ic_broken_image_black))
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemListFullBinding =
            ItemListFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemListFullBinding)
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