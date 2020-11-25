package com.ysshin.fine_dust_app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.AuthData
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.databinding.FragmentLoginBinding
import com.ysshin.fine_dust_app.viewmodel.LoginViewModel
import com.ysshin.fine_dust_app.viewmodel.LoginViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(AuthService.create())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        login()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loginButton.setOnClickListener {
                login()
            }

            registrationButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }

    }

    private fun login() {
        viewModel.setLoading(true)
        val call = viewModel.login()
        call.enqueue(object : Callback<AuthData> {
            override fun onResponse(call: Call<AuthData>, response: Response<AuthData>) {
                val authData = response.body()
                if (authData == null)
                    viewModel.clearPassword()
                else {
                    authData.apply {
                        val context = requireContext()
                        PreferenceManager(context).saveToken(token)
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }
                }
                viewModel.setLoading(false)
            }

            override fun onFailure(call: Call<AuthData>, t: Throwable) {
                Log.e("Login", "${t.message}")
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