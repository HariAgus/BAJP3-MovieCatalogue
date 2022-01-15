package com.hariagus.finalproject.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hariagus.finalproject.R
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.ItemListBinding
import com.hariagus.finalproject.utils.loadImage


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

    private var listener: ((MovieEntity) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowAdapter.TvShowViewHolder {
        val itemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TvShowViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow, listener)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: MovieEntity, listener: ((MovieEntity) -> Unit)?) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvScore.text = tvShow.voteAverage.toString()
                itemView.context.loadImage(
                    itemView.context.getString(R.string.url_poster, tvShow.posterPath),
                    roundedPoster
                )
            }

            itemView.setOnClickListener {
                listener?.invoke(tvShow)
            }
        }

    }

    fun onClick(listener: ((MovieEntity) -> Unit)?) {
        this.listener = listener
    }

}