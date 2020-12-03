package com.ysshin.fine_dust_app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.data.Auth
import com.ysshin.fine_dust_app.data.Token
import com.ysshin.fine_dust_app.databinding.FragmentLoginBinding
import com.ysshin.fine_dust_app.utils.PreferenceManager
import com.ysshin.fine_dust_app.viewmodels.LoginViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()
    private val preferenceManager: PreferenceManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.loginButton.setOnClickListener {
            login()
        }
        binding.registrationButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isAdded)
            return

        autoLogin()
    }

    private fun autoLogin() {
        val token = preferenceManager.getToken() ?: return
        viewModel.setLoading(true)
        val call = viewModel.verifyToken(token)
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                viewModel.setLoading(false)
                val tokenResponse = response.body() ?: return
                if (token != tokenResponse.token)
                    return
                val activity = requireActivity()
                val intent = Intent(activity, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                activity.finish()
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.e("Auth login", "${t.message}")
                preferenceManager.clearToken()
                viewModel.setLoading(false)
            }
        })
    }

    private fun login() {
        viewModel.setLoading(true)
        val call = viewModel.login()
        call.enqueue(object : Callback<Auth> {
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                viewModel.setLoading(false)
                val authData = response.body()
                if (authData == null) {
                    viewModel.clearPassword()
                } else {
                    authData.apply {
                        preferenceManager.saveToken(token)
                        viewModel.setLoading(false)
                        val activity = requireActivity()
                        val intent = Intent(activity, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                        activity.finish()
                    }
                }
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                Log.e("Login", "${t.message}")
                viewModel.clearPassword()
                viewModel.setLoading(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}