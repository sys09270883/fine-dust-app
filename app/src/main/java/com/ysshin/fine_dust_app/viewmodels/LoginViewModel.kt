package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysshin.fine_dust_app.data.Login
import com.ysshin.fine_dust_app.data.LoginRepository
import com.ysshin.fine_dust_app.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {
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

    val loggedIn: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            postValue(false)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        loading.postValue(isLoading)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        loggedIn.postValue(isLoggedIn)
    }

    private fun clearPassword() {
        password.postValue("")
    }

    private fun getLoginData(): Login = Login(username.value ?: "", password.value ?: "")

    fun login() {
        setLoading(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val auth = repository.login(getLoginData())
                preferenceManager.saveToken(auth.token)
                setLoggedIn(true)
            } catch (e: Exception) {
                clearPassword()
            } finally {
                setLoading(false)
            }
        }
    }

    fun autoLogin() {
        setLoading(true)
        val currentToken = preferenceManager.getToken() ?: ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.verifyToken(currentToken)
                if (token.token == currentToken) {
                    setLoggedIn(true)
                } else {
                    throw Exception()
                }
            } catch (e: Exception) {
                preferenceManager.clearToken()
            } finally {
                setLoading(false)
            }
        }
    }
}