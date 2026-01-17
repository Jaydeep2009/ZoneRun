package com.jaydeep.zonerun.model

data class User(
    val uid: String = "",
    val email: String = "",
    val username: String = "",
    val totalDistance: Double = 0.0,
    val totalRuns: Int = 0,
    val joinedAt: Long = System.currentTimeMillis()
)
