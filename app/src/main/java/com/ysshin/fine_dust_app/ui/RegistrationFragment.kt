package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.AuthInfo
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.data.RegistrationInfo
import com.ysshin.fine_dust_app.databinding.FragmentRegistrationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.submitButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val registrationInfo = RegistrationInfo(
            binding.username.text.toString(),
            binding.email.text.toString(),
            binding.password1.text.toString(),
            binding.password2.text.toString(),
            binding.firstName.text.toString(),
            binding.lastName.text.toString()
        )

        val call = AuthService.create().register(registrationInfo)

        call.enqueue(object : Callback<AuthInfo> {
            override fun onResponse(call: Call<AuthInfo>, response: Response<AuthInfo>) {
                val registrationResult = response.body()
                registrationResult?.apply {
                    PreferenceManager(requireContext()).saveToken(token)
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                }
            }

            override fun onFailure(call: Call<AuthInfo>, t: Throwable) {
                Log.e("Registration", "${t.message}")
            }

        })
    }
}