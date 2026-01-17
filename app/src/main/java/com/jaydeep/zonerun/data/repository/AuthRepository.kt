package com.jaydeep.zonerun.data.repository


import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthRepository{
    private val auth = FirebaseAuth.getInstance()

    // Check if user is logged in
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
    // Login user with email and password
    suspend fun login(email: String,password:String){
        auth.signInWithEmailAndPassword(email,password).await()
    }
    // Signup user with email and password
    suspend fun signup(email: String,password:String){
        auth.createUserWithEmailAndPassword(email,password).await()
    }
    // Logout user
    fun logout(){
        auth.signOut()
    }
}