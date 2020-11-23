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
import com.ysshin.fine_dust_app.data.AuthInfo
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.databinding.FragmentRegistrationBinding
import com.ysshin.fine_dust_app.viewmodel.RegistrationViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel by activityViewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
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

    override fun onResume() {
        super.onResume()
        Log.d("username", "${viewModel.username.value}")
    }

    private fun register() {
        val registrationInfo = viewModel.getRegistrationInfo()
        val call = AuthService.create().register(registrationInfo)
        viewModel.setLoading(true)

        call.enqueue(object : Callback<AuthInfo> {
            override fun onResponse(call: Call<AuthInfo>, response: Response<AuthInfo>) {
                val registrationResult = response.body()
                registrationResult?.apply {
                    PreferenceManager(requireContext()).saveToken(token)
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                }
                viewModel.setLoading(false)
                viewModel.clearAll()
            }

            override fun onFailure(call: Call<AuthInfo>, t: Throwable) {
                Log.e("Registration", "${t.message}")
                viewModel.setLoading(false)
                viewModel.clearPassword()
            }

        })
    }
}