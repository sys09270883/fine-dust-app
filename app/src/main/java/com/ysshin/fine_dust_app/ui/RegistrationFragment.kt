package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.AuthData
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.databinding.FragmentRegistrationBinding
import com.ysshin.fine_dust_app.viewmodel.RegistrationViewModel
import com.ysshin.fine_dust_app.viewmodel.RegistrationViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<RegistrationViewModel> {
        RegistrationViewModelFactory(AuthService.create())
    }

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
        binding.submitButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        viewModel.setLoading(true)
        val call = viewModel.register()
        call.enqueue(object : Callback<AuthData> {
            override fun onResponse(call: Call<AuthData>, response: Response<AuthData>) {
                val authInfo = response.body()
                if (authInfo == null)
                    viewModel.clearPassword()
                else {
                    authInfo.apply {
                        PreferenceManager(requireContext()).saveToken(token)
                        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                    }
                    viewModel.clearAll()
                }
                viewModel.setLoading(false)
            }

            override fun onFailure(call: Call<AuthData>, t: Throwable) {
                Log.e("Registration", "${t.message}")
                viewModel.setLoading(false)
                viewModel.clearPassword()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}