package com.ysshin.fine_dust_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.api.AuthService
import com.ysshin.fine_dust_app.data.AuthData
import com.ysshin.fine_dust_app.data.PreferenceManager
import com.ysshin.fine_dust_app.data.RegistrationData
import com.ysshin.fine_dust_app.repository.RegistrationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel(authService: AuthService) : ViewModel() {

    val repository = RegistrationRepository(authService)

    val username: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val password1: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val password2: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val firstName: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }

    val lastName: MutableLiveData<String> by lazy {
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
        password1.value = ""
        password2.value = ""
    }

    fun clearAll() {
        username.value = ""
        email.value = ""
        firstName.value = ""
        lastName.value = ""
        clearPassword()
    }

    fun getRegistrationInfo() = RegistrationData(
        username.value ?: "",
        email.value ?: "",
        password1.value ?: "",
        password2.value ?: "",
        firstName.value ?: "",
        lastName.value ?: ""
    )

    fun register(): Call<AuthData> = repository.register(getRegistrationInfo())

}