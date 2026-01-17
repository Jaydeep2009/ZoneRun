package com.jaydeep.zonerun.run
import com.google.android.gms.maps.model.LatLng

data class RunState(
    val isRunning: Boolean = false,
    val distance: Float = 0f,        // meters
    val duration: Long = 0L,         // millis
    val path: List<LatLng> = emptyList()
)
