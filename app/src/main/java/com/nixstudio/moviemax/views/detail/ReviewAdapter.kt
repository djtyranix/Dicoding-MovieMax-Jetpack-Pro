package com.nixstudio.moviemax.views.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.data.utils.reviews.ReviewsItem
import com.nixstudio.moviemax.databinding.ItemListReviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private val listReview = ArrayList<ReviewsItem>()

    fun setReviews(reviews: List<ReviewsItem>?) {
        if (reviews == null) return

        listReview.clear()
        listReview.addAll(reviews)
        notifyDataSetChanged()
    }

    inner class ReviewViewHolder(private val binding: ItemListReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ReviewsItem) {
            binding.tvAuthor.text = review.author

            if (review.authorDetails?.rating != null) {
                if (review.authorDetails.rating < 10 && review.authorDetails.rating >= 0) {
                    binding.tvRatingReview.text =
                        binding.tvRatingReview.context.resources.getString(
                            R.string.rating_value,
                            review.authorDetails.rating.toString()
                        )
                } else if (review.authorDetails.rating == 10.0) {
                    binding.tvRatingReview.text =
                        binding.tvRatingReview.context.resources.getString(
                            R.string.rating_value,
                            review.authorDetails.rating.toInt().toString()
                        )
                }

            } else {
                binding.tvRatingReview.text =
                    binding.tvRatingReview.context.resources.getString(R.string.rating_value, "-")
            }

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

            var url: String? = null
            if (review.authorDetails?.avatarPath != null) {
                val currentUrl = review.authorDetails.avatarPath
                url = if (currentUrl.contains("https")) {
                    currentUrl.removeRange(0, 1)
                } else {
                    "https://image.tmdb.org/t/p/w185${currentUrl}"
                }
            }

            Glide.with(binding.imgAvatar.context)
                .load(url)
                .apply(
                    RequestOptions().override(200, 200).placeholder(shimmerDrawable)
                        .error(R.drawable.ic_profile)
                )
                .into(binding.imgAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemListReviewBinding =
            ItemListReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(itemListReviewBinding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = listReview[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = listReview.size


}