package com.nixstudio.moviemax.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemListMainBinding
import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.sources.remote.DiscoverMovieResultsItem

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

            val url = "https://image.tmdb.org/t/p/original${movie.posterPath}"
            Glide.with(binding.imgPoster.context)
                .load(url)
                .apply(RequestOptions().override(400, 600).error(R.drawable.ic_broken_image_black))
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