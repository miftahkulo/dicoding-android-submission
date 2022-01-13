package com.indramahkota.app.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.indramahkota.app.databinding.VhItemEmptyBinding
import com.indramahkota.app.databinding.VhMovieBinding
import com.indramahkota.app.databinding.VhMovieLoadingBinding
import com.indramahkota.app.ui.widget.EmptyViewHolder
import com.indramahkota.common.base.BaseRecyclerAdapter
import com.indramahkota.common.base.EmptyModel
import com.indramahkota.common.utils.Constant
import com.indramahkota.common.utils.Constant.TYPE_EMPTY
import com.indramahkota.common.utils.Constant.TYPE_LOADING
import com.indramahkota.common.utils.dpToPx
import com.indramahkota.domain.model.Movie

private class LoadingItemViewHolder(binding: VhMovieLoadingBinding) :
    RecyclerView.ViewHolder(binding.root)

private class MovieItemViewHolder(val binding: VhMovieBinding) :
    RecyclerView.ViewHolder(binding.root)

class MovieAdapter(
    private val listener: (Movie) -> Unit
) : BaseRecyclerAdapter() {
    private lateinit var context: Context
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        this.inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_LOADING -> LoadingItemViewHolder(
                VhMovieLoadingBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            TYPE_EMPTY -> EmptyViewHolder(
                VhItemEmptyBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> MovieItemViewHolder(
                VhMovieBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                val item = get(position) as Movie

                with(holder.binding) {
                    txtTitle.text = item.title
                    txtOverview.text = item.overview
                    txtRating.text = item.voteAverage.toString()
                    txtReleaseDate.text = item.releaseDate

                    if (item.favorite) imgBookmark.visibility =
                        View.VISIBLE else imgBookmark.visibility = View.GONE

                    if (position == itemCount - 1)
                        divider.visibility = View.GONE
                    else
                        divider.visibility = View.VISIBLE

                    Glide.with(context)
                        .load("${Constant.BASE_IMAGE_URL}/${item.posterPath}")
                        .transform(RoundedCorners(8.dpToPx(holder.itemView.context).toInt()))
                        .into(imgPoster)
                }

                holder.itemView.setOnClickListener {
                    listener(item)
                }
            }
            is EmptyViewHolder -> {
                val item = get(position) as EmptyModel
                with(holder.binding) {
                    tvDescription.text = item.message
                }
            }
        }
    }
}