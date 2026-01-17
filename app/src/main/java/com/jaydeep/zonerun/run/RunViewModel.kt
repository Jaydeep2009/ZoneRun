package com.jaydeep.zonerun.run

import android.app.Application
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jaydeep.zonerun.data.repository.RunRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RunViewModel(application: Application): AndroidViewModel(application){
    private var timerJob: Job? = null

    private val repo = RunRepository(application)

    var state by mutableStateOf(RunState())
        private set

    private var startTime = 0L
    private var lastLocation : Location?=null

    fun startRun(){
        startTime = System.currentTimeMillis()
        lastLocation=null
        state = RunState(isRunning = true)

        startTimer()

        repo.startTracking{
            location ->
            onNewLocation(location)
        }
    }

    fun stopRun(){
        repo.stopTracking()
        stopTimer()
        state = state.copy(isRunning = false)
    }

    private fun onNewLocation(location: Location){
        val newDistance = if(lastLocation!=null)
            lastLocation!!.distanceTo(location)
        else 0f

        lastLocation = location

        val newPath = state.path + (location.latitude to location.longitude)
        val newDuration = System.currentTimeMillis() - startTime

        state = state.copy(
            distance = state.distance + newDistance,
            duration = newDuration,
            path = newPath
        )
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                val elapsed = System.currentTimeMillis() - startTime
                state = state.copy(duration = elapsed)
                delay(1000L)
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
    }


}