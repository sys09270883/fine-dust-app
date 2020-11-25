package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.AuthData
import com.ysshin.fine_dust_app.data.LoginData
import com.ysshin.fine_dust_app.data.LoginRepository
import retrofit2.Call

class LoginViewModel(authService: AuthService) : ViewModel() {
    private val repository = LoginRepository(authService)

    val username: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            postValue(false)
        }
    }

    fun setLoading(isLoading: Boolean) {
        loading.value = isLoading
    }

    fun clearPassword() {
        password.value = ""
    }

    private fun getLoginData(): LoginData = LoginData(username.value ?: "", password.value ?: "")

    fun login(): Call<AuthData> = repository.login(getLoginData())
}