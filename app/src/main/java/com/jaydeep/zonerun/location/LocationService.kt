package com.jaydeep.zonerun.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationService(context: Context) {
    private val client = LocationServices.getFusedLocationProviderClient(context)
    private var callback: LocationCallback?=null

    @SuppressLint("MissingPermission")
    fun start(onLocation: (Location)->Unit){
        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            2000L
        ).build()

        callback=object: LocationCallback(){
            override fun onLocationResult(result: LocationResult) {
                result.locations.forEach { onLocation(it) }
            }
        }

        client.requestLocationUpdates(request,callback!!,Looper.getMainLooper())
    }

    fun stop(){
        callback?.let {
            client.removeLocationUpdates(it)
        }
    }
}