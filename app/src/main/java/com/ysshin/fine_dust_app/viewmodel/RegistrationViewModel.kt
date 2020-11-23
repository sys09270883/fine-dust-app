package com.ysshin.fine_dust_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.RegistrationData

class RegistrationViewModel() : ViewModel() {
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

}