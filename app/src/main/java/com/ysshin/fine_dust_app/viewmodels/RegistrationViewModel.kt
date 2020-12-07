package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysshin.fine_dust_app.data.Registration
import com.ysshin.fine_dust_app.data.RegistrationRepository
import com.ysshin.fine_dust_app.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val repository: RegistrationRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {
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

    val authCreated: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            postValue(false)
        }
    }

    fun setCreated(isAuthCreated: Boolean) {
        authCreated.postValue(isAuthCreated)
    }

    private fun setLoading(isLoading: Boolean) {
        loading.postValue(isLoading)
    }

    private fun clearPassword() {
        password1.postValue("")
        password2.postValue("")
    }

    private fun clearAll() {
        username.postValue("")
        email.postValue("")
        firstName.postValue("")
        lastName.postValue("")
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

    fun register() {
        setLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val auth = repository.register(getRegistrationData())
                preferenceManager.saveToken(auth.token)
                setCreated(true)
                clearAll()
            } catch (e: Exception) {
                clearPassword()
            } finally {
                loading.postValue(false)
            }
        }
    }

}