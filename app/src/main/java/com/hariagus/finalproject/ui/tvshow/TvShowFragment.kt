package com.hariagus.finalproject.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.databinding.FragmentTvShowBinding
import com.hariagus.finalproject.utils.SortUtils
import com.hariagus.finalproject.vo.Resource
import com.hariagus.finalproject.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(
            layoutInflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowAdapter = TvShowAdapter()
        setList(SortUtils.NEWEST)

        binding?.progressSpinKitList?.visibility = View.VISIBLE

        with(binding?.rvTvShow) {
            this?.setHasFixedSize(true)
            this?.adapter = tvShowAdapter
        }

        binding?.fabNewest?.setOnClickListener { setList(SortUtils.NEWEST) }
        binding?.fabOldest?.setOnClickListener { setList(SortUtils.OLDEST) }
        binding?.fabPopularity?.setOnClickListener { setList(SortUtils.POPULARITY) }
    }

    private fun setList(newest: String) {
        viewModel.getTvShow(newest).observe(requireActivity(), tvShowObserver)
    }

    private val tvShowObserver = Observer<Resource<PagedList<MovieEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> binding?.progressSpinKitList?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressSpinKitList?.visibility = View.GONE
                    tvShowAdapter.submitList(tvShow.data)
                    tvShowAdapter.notifyDataSetChanged()
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
        _fragmentTvShowBinding = null
    }

}