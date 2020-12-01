package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.Auth
import com.ysshin.fine_dust_app.data.Registration
import com.ysshin.fine_dust_app.data.RegistrationRepository
import retrofit2.Call

class RegistrationViewModel(private val repository: RegistrationRepository) : ViewModel() {

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

    private fun getRegistrationData() = Registration(
        username.value ?: "",
        email.value ?: "",
        password1.value ?: "",
        password2.value ?: "",
        firstName.value ?: "",
        lastName.value ?: ""
    )

    fun register(): Call<Auth> = repository.register(getRegistrationData())

}