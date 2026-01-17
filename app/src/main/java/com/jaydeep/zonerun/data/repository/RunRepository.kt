package com.jaydeep.zonerun.data.repository

import android.content.Context
import android.location.Location
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jaydeep.zonerun.location.LocationService
import com.jaydeep.zonerun.model.Run
import kotlinx.coroutines.tasks.await

class RunRepository(context: Context) {

    private val locationService = LocationService(context)
    private val db= FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    fun startTracking(onLocation: (Location) -> Unit) {
        locationService.start(onLocation)
    }

    fun stopTracking() {
        locationService.stop()
    }

    suspend fun saveRun(run: Run){
        val uid = auth.currentUser?.uid?:return
        val doc = db.collection("runs").document()
        val runWithId = run.copy(id = doc.id, userId = uid)
        doc.set(runWithId).await()
    }
}