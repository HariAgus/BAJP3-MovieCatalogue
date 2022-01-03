package com.hariagus.finalproject.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hariagus.finalproject.R
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.ActivityDetailBinding
import com.hariagus.finalproject.vo.Resource
import com.hariagus.finalproject.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val typeEnum: TypeDetail = TypeDetail.values()[type]
        val id = intent.getIntExtra(ID_DATA, -1)

        binding.svLoadingDetail.visibility = View.VISIBLE
        binding.nestedScroll.visibility = View.GONE
        when (typeEnum) {
            TypeDetail.MOVIE -> {
                viewModel.setSelectedMovie(id)
                viewModel.movieDetail.observe(this, { movie ->
                    if (movie != null) {
                        showDetailData(movie)
                    }
                })
            }
            TypeDetail.TV_SHOW -> {
                viewModel.setSelectedTvShow(id)
                viewModel.tvShowDetail.observe(this, { tvShow ->
                    if (tvShow != null) {
                        showDetailData(tvShow)
                    }
                })
            }
        }

        binding.fabFavorite.setOnClickListener {
            when (typeEnum) {
                TypeDetail.MOVIE -> {
                    viewModel.setFavoriteMovie()
                }
                TypeDetail.TV_SHOW -> {
                    viewModel.setFavoriteTvShow()
                }
            }
        }
    }

    private fun showDetailData(movie: Resource<MovieEntity>) {
        when (movie.status) {
            Status.LOADING -> binding.svLoadingDetail.visibility = View.VISIBLE
            Status.SUCCESS -> if (movie.data != null) {
                binding.svLoadingDetail.visibility = View.GONE
                binding.nestedScroll.visibility = View.VISIBLE

                val state = movie.data.isFavorite
                setFavorite(state)
                loadDetailData(movie.data)
            }
            Status.ERROR -> {
                binding.svLoadingDetail.visibility = View.GONE
                Toast.makeText(this, "There is an Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_dark
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.favorite_border
                )
            )
        }
    }

    @SuppressLint("CheckResult")
    private fun loadDetailData(movieEntity: MovieEntity) {
        with(binding) {
            tvTitleDetail.text = movieEntity.title
            tvLanguage.text = movieEntity.originalLanguage
            tvPopularity.text = movieEntity.popularity.toString()
            tvOverview.text = movieEntity.overview
            tvReleaseDate.text = movieEntity.releaseDate
            tvScoreDetail.text = movieEntity.voteAverage.toString()

            /**
             * Poster Detail
             */
            Glide.with(this@DetailActivity)
                .load(this@DetailActivity.getString(R.string.url_poster, movieEntity.posterPath))
                .apply {
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.loading_animation)
                }
                .into(roundedPosterDetail)

            /**
             * Background Detail
             */
            Glide.with(this@DetailActivity)
                .load(this@DetailActivity.getString(R.string.url_poster, movieEntity.backdropPath))
                .apply {
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.loading_animation)
                }
                .into(posterBg)
        }
    }

    companion object {
        const val EXTRA_TYPE = "type"
        const val ID_DATA = "id_data"
    }

}