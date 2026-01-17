package com.jaydeep.zonerun.auth

data class AuthState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null

)