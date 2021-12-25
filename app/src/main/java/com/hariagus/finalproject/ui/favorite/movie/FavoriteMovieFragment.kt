package com.hariagus.finalproject.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hariagus.finalproject.R
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.FragmentFavoriteMovieBinding
import com.hariagus.finalproject.ui.favorite.FavoriteViewModel
import com.hariagus.finalproject.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _fragmentFavoriteMovieBinding

    private lateinit var favoriteAdapter: FavoriteMovieAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _fragmentFavoriteMovieBinding =
            FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvMovieFavorite)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        favoriteAdapter = FavoriteMovieAdapter()

        binding?.progressSpinKitList?.visibility = View.VISIBLE
        viewModel.getFavoriteMovies().observe(this, { movies ->
            binding?.progressSpinKitList?.visibility = View.GONE
            favoriteAdapter.submitList(movies)
        })

        with(binding?.rvMovieFavorite) {
            this?.setHasFixedSize(true)
            this?.adapter = favoriteAdapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favoriteAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavoriteDataMovie(it) }
                val snackBar = Snackbar.make(view as View, R.string.undo_delete, Snackbar.LENGTH_LONG)
                snackBar.setAction("OK") { _ ->
                    movieEntity?.let { viewModel.setFavoriteDataMovie(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteMovieBinding = null
    }
}