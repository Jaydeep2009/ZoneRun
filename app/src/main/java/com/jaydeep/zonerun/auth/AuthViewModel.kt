package com.jaydeep.zonerun.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaydeep.zonerun.data.repository.AuthRepository
import com.jaydeep.zonerun.data.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel(){
    private val repository = AuthRepository()
    private val userRepository = UserRepository()


    var state by mutableStateOf(AuthState())
        private set

    init{
        state= state.copy(isLoggedIn = repository.isUserLoggedIn())
    }

    fun login(email:String,password:String){
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            try {
                repository.login(email,password)

                userRepository.createUserIfNotExists()
                state = state.copy(isLoading = false, isLoggedIn = true)
            }catch (e:Exception){
                state = state.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun signup(email:String,password:String){
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            try {
                repository.signup(email,password)

                userRepository.createUserIfNotExists()
                state = state.copy(isLoading = false, isLoggedIn = true)
            }catch (e:Exception){
                state = state.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun logout(){
        repository.logout()
        state = AuthState()
    }
}