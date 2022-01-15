package com.hariagus.finalproject.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hariagus.finalproject.R
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.FragmentMovieBinding
import com.hariagus.finalproject.ui.detail.DetailActivity
import com.hariagus.finalproject.ui.detail.TypeDetail
import com.hariagus.finalproject.utils.*
import com.hariagus.finalproject.vo.Resource
import com.hariagus.finalproject.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMoviesBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMoviesBinding!!

    private lateinit var moviesAdapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMoviesBinding = FragmentMovieBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MovieAdapter()
        setList(SortUtils.NEWEST)

        binding.progressSpinKitList.visible()

        with(binding.rvMovie) {
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        binding.apply {
            fabNewest.setOnClickListener { setList(SortUtils.NEWEST) }
            fabOldest.setOnClickListener { setList(SortUtils.OLDEST) }
            fabPopularity.setOnClickListener { setList(SortUtils.POPULARITY) }
        }

        onClick()
    }

    private fun onClick() {
        moviesAdapter.onClick { data ->
            requireContext().startActivity<DetailActivity>(
                DetailActivity.EXTRA_TYPE to TypeDetail.MOVIE.ordinal,
                DetailActivity.ID_DATA to data.id
            )
        }
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(requireActivity(), moviesObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val moviesObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> binding.progressSpinKitList.visible()
                Status.SUCCESS -> {
                    binding.progressSpinKitList.gone()
                    moviesAdapter.apply {
                        submitList(movies.data)
                        notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    binding.progressSpinKitList.gone()
                    context?.toast(getString(R.string.message_error))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMoviesBinding = null
    }

}