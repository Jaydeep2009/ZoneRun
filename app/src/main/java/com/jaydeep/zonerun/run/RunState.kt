package com.jaydeep.zonerun.run

data class RunState(
    val isRunning: Boolean = false,
    val distance: Float = 0f,        // meters
    val duration: Long = 0L,         // millis
    val path: List<Pair<Double, Double>> = emptyList()
)
