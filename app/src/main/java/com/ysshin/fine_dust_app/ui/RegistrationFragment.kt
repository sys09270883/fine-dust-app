package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.databinding.FragmentRegistrationBinding
import com.ysshin.fine_dust_app.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.authCreated.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    viewModel.setCreated(false)
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                }
            }
        }
        binding.submitButton.setOnClickListener {
            viewModel.register()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}