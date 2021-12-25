package com.hariagus.finalproject.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.FragmentMovieBinding
import com.hariagus.finalproject.utils.SortUtils
import com.hariagus.finalproject.viewmodel.ViewModelFactory
import com.hariagus.finalproject.vo.Resource
import com.hariagus.finalproject.vo.Status

class MovieFragment : Fragment() {

    private var _fragmentMoviesBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMoviesBinding

    private lateinit var viewModel: MovieViewModel
    private lateinit var moviesAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMoviesBinding = FragmentMovieBinding.inflate(
            layoutInflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        moviesAdapter = MovieAdapter()
        setList(SortUtils.NEWEST)

        binding?.progressSpinKitList?.visibility = View.VISIBLE

        with(binding?.rvMovie) {
            this?.setHasFixedSize(true)
            this?.adapter = moviesAdapter
        }

        binding?.fabNewest?.setOnClickListener { setList(SortUtils.NEWEST) }
        binding?.fabOldest?.setOnClickListener { setList(SortUtils.OLDEST) }
        binding?.fabPopularity?.setOnClickListener { setList(SortUtils.POPULARITY) }
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(this, moviesObserver)
    }

    private val moviesObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> binding?.progressSpinKitList?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressSpinKitList?.visibility = View.GONE
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding?.progressSpinKitList?.visibility = View.GONE
                    Toast.makeText(context, "There is an Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMoviesBinding = null
    }

}