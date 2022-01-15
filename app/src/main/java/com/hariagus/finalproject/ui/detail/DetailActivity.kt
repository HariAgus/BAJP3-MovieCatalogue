package com.hariagus.finalproject.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hariagus.finalproject.R
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.ActivityDetailBinding
import com.hariagus.finalproject.utils.*
import com.hariagus.finalproject.vo.Resource
import com.hariagus.finalproject.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private var movie: Resource<MovieEntity>? = null
    private var tvShow: Resource<MovieEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val typeEnum: TypeDetail = TypeDetail.values()[type]
        val id = intent.getIntExtra(ID_DATA, -1)

        binding.apply {
            svLoadingDetail.visible()
            nestedScroll.gone()
        }

        when (typeEnum) {
            TypeDetail.MOVIE -> {
                viewModel.setSelectedMovie(id)
                viewModel.movieDetail.observe(this, { movie ->
                    if (movie != null) {
                        this.movie = movie
                        showDetailData(movie)
                    }
                })
            }
            TypeDetail.TV_SHOW -> {
                viewModel.setSelectedTvShow(id)
                viewModel.tvShowDetail.observe(this, { tvShow ->
                    if (tvShow != null) {
                        this.tvShow = tvShow
                        showDetailData(tvShow)
                    }
                })
            }
        }

        binding.fabFavorite.setOnClickListener {
            setDataToFavorite(typeEnum)
        }
    }

    private fun setDataToFavorite(typeEnum: TypeDetail) {
        when (typeEnum) {
            TypeDetail.MOVIE -> {
                if (movie?.data?.isFavorite != true) {
                    viewModel.setFavoriteMovie()
                    successToast(
                        getString(R.string.success),
                        getString(R.string.message_add_favorite)
                    )
                } else {
                    viewModel.setFavoriteMovie()
                    infoToast(
                        getString(R.string.info),
                        getString(R.string.message_remove_favorite)
                    )
                }
            }
            TypeDetail.TV_SHOW -> {
                if (tvShow?.data?.isFavorite != true) {
                    viewModel.setFavoriteTvShow()
                    successToast(
                        getString(R.string.success),
                        getString(R.string.message_add_favorite)
                    )
                } else {
                    viewModel.setFavoriteTvShow()
                    infoToast(
                        getString(R.string.info),
                        getString(R.string.message_remove_favorite)
                    )
                }
            }
        }
    }

    private fun showDetailData(movie: Resource<MovieEntity>) {
        when (movie.status) {
            Status.LOADING -> binding.svLoadingDetail.visible()
            Status.SUCCESS -> if (movie.data != null) {
                binding.svLoadingDetail.gone()
                binding.nestedScroll.visible()

                val state = movie.data.isFavorite
                setFavorite(state)
                loadDetailData(movie.data)
            }
            Status.ERROR -> {
                binding.svLoadingDetail.gone()
                toast(getString(R.string.message_error))
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

            // Poster Detail
            loadImage(
                this@DetailActivity.getString(
                    R.string.url_poster, movieEntity.posterPath
                ),
                roundedPosterDetail
            )
            // Background Detail
            loadImage(
                this@DetailActivity.getString(
                    R.string.url_poster, movieEntity.backdropPath
                ),
                posterBg
            )
        }
    }

    companion object {
        const val EXTRA_TYPE = "type"
        const val ID_DATA = "id_data"
    }

}