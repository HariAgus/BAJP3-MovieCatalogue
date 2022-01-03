package com.hariagus.finalproject.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hariagus.finalproject.R
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.ItemListBinding
import com.hariagus.finalproject.ui.detail.DetailActivity
import com.hariagus.finalproject.ui.detail.TypeDetail

class
FavoriteTvShowAdapter : PagedListAdapter<MovieEntity, FavoriteTvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowAdapter.ViewHolder {
        val itemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvScore.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.url_poster, movie.posterPath))
                    .apply {
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_animation)
                    }
                    .into(roundedPoster)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_TYPE, TypeDetail.MOVIE.ordinal)
                    putExtra(DetailActivity.ID_DATA, movie.id)
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}