package com.hariagus.finalproject.ui.tvshow

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
import com.hariagus.finalproject.ui.detail.DetailActivity.Companion.EXTRA_TYPE
import com.hariagus.finalproject.ui.detail.DetailActivity.Companion.ID_DATA
import com.hariagus.finalproject.ui.detail.TypeDetail


class TvShowAdapter : PagedListAdapter<MovieEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvShowViewHolder {
        val itemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TvShowViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(tvShow: MovieEntity) {
                with(binding) {
                    tvTitle.text = tvShow.title
                    tvScore.text = tvShow.voteAverage.toString()
                    Glide.with(itemView.context)
                        .load(itemView.context.getString(R.string.url_poster, tvShow.posterPath))
                        .apply {
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.loading_animation)
                        }
                        .into(roundedPoster)
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(EXTRA_TYPE, TypeDetail.TV_SHOW.ordinal)
                        putExtra(ID_DATA, tvShow.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
}