package com.jaydeep.zonerun.data.repository

import android.content.Context
import android.location.Location
import com.jaydeep.zonerun.location.LocationService

class RunRepository(context: Context) {

    private val locationService = LocationService(context)

    fun startTracking(onLocation: (Location) -> Unit) {
        locationService.start(onLocation)
    }

    fun stopTracking() {
        locationService.stop()
    }
}