package com.nixstudio.moviemax.views.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.utils.credits.CastItem
import com.nixstudio.moviemax.databinding.ItemListCastBinding
import com.nixstudio.moviemax.databinding.ItemListMainBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private val listCast = ArrayList<CastItem>()

    fun setCasts(casts: List<CastItem>?) {
        if (casts == null) return

        listCast.clear()
        listCast.addAll(casts)
        notifyDataSetChanged()
    }

    inner class CastViewHolder(private val binding: ItemListCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: CastItem) {
            binding.tvTitle.text = cast.name

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

            val url = "https://image.tmdb.org/t/p/w185${cast.profilePath}"
            Glide.with(binding.imgPoster.context)
                .load(url)
                .apply(
                    RequestOptions().override(400, 600).placeholder(shimmerDrawable)
                        .error(R.drawable.ic_profile)
                )
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemListCastBinding =
            ItemListCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(itemListCastBinding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = listCast[position]
        holder.bind(cast)
    }

    override fun getItemCount(): Int = listCast.size
}