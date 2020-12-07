package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ysshin.fine_dust_app.databinding.FragmentHomeBinding
import com.ysshin.fine_dust_app.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initLocation()
        initRefreshLayout()
    }

    private fun initRefreshLayout() {
        val refreshLayout = binding.container
        refreshLayout.setOnRefreshListener {
            viewModel.fetchAllInformation()
            refreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.needUpdate())
            return

        viewModel.fetchAllInformation()
    }

}