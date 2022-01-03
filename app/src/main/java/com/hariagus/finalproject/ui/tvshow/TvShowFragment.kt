package com.hariagus.finalproject.ui.tvshow

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
import com.hariagus.finalproject.databinding.FragmentTvShowBinding
import com.hariagus.finalproject.utils.SortUtils
import com.hariagus.finalproject.utils.gone
import com.hariagus.finalproject.utils.toast
import com.hariagus.finalproject.utils.visible
import com.hariagus.finalproject.vo.Resource
import com.hariagus.finalproject.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding!!

    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowAdapter = TvShowAdapter()
        setList(SortUtils.NEWEST)

        binding.progressSpinKitList.visible()

        with(binding.rvTvShow) {
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        binding.apply {
            fabNewest.setOnClickListener { setList(SortUtils.NEWEST) }
            fabOldest.setOnClickListener { setList(SortUtils.OLDEST) }
            fabPopularity.setOnClickListener { setList(SortUtils.POPULARITY) }
        }
    }

    private fun setList(newest: String) {
        viewModel.getTvShow(newest).observe(requireActivity(), tvShowObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val tvShowObserver = Observer<Resource<PagedList<MovieEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> binding.progressSpinKitList.visible()
                Status.SUCCESS -> {
                    binding.progressSpinKitList.gone()
                    tvShowAdapter.apply {
                        submitList(tvShow.data)
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
        _fragmentTvShowBinding = null
    }

}