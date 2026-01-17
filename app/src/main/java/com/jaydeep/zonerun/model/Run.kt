package com.jaydeep.zonerun.model

import com.google.android.gms.maps.model.LatLng

data class Run(
    val id: String = "",
    val userId: String = "",
    val distance: Float = 0f,        // meters
    val duration: Long = 0L,         // millis
    val path: List<LatLng> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
)
