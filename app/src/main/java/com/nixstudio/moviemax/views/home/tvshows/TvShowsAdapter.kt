package com.nixstudio.moviemax.views.home.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.ItemListFullBinding
import com.nixstudio.moviemax.models.TvShowsEntity

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listTv = ArrayList<TvShowsEntity>()

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShowsEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTv(tvShows: List<TvShowsEntity>?) {
        if (tvShows == null) return

        this.listTv.clear()
        this.listTv.addAll(tvShows)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(private val binding: ItemListFullBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShowsEntity) {
            binding.tvTitle.text = movie.tvTitle

            Glide.with(binding.imgPoster.context)
                .load(movie.tvPoster)
                .apply(RequestOptions().override(400, 600).error(R.drawable.ic_broken_image_black))
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsTvBinding =
            ItemListFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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