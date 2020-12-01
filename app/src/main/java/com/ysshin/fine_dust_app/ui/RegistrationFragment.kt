package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.data.Auth
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.databinding.FragmentRegistrationBinding
import com.ysshin.fine_dust_app.viewmodels.RegistrationViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()
    private val preferenceManager: PreferenceManager by inject()

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
        call.enqueue(object : Callback<Auth> {
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                val authData = response.body()
                if (authData == null)
                    viewModel.clearPassword()
                else {
                    authData.apply {
                        preferenceManager.saveToken(token)
                        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                    }
                    viewModel.clearAll()
                }
                viewModel.setLoading(false)
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
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