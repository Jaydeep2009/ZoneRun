package com.jaydeep.zonerun.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jaydeep.zonerun.model.User
import kotlinx.coroutines.tasks.await

class UserRepository{
    private val db= FirebaseFirestore.getInstance()
    private val auth= FirebaseAuth.getInstance()

    suspend fun createUserIfNotExists(){
        val currentUser = auth.currentUser ?: return
        val userRef = db.collection("users").document(currentUser.uid)

        val snapshot = userRef.get().await()

        if(!snapshot.exists()){
            val user = User(
                uid = currentUser.uid,
                email = currentUser.email ?: "",
                username = currentUser.email?.substringBefore("@") ?: ""
            )
            userRef.set(user).await()
        }
    }

    suspend fun getUser():User?{
        val uid = auth.currentUser?.uid ?: return null
        val snapshot = db.collection("users").document(uid).get().await()
        return snapshot.toObject(User::class.java)
    }
}