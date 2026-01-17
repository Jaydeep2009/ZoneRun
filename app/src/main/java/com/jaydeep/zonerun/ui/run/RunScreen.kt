package com.jaydeep.zonerun.ui.run

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.jaydeep.zonerun.run.RunViewModel

@Composable
fun RunScreen(viewModel: RunViewModel = viewModel()) {

    val context = LocalContext.current
    val state = viewModel.state

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val cameraPositionState = rememberCameraPositionState()

    Box(Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true)
        ) {

            if (state.path.size > 1) {
                Polyline(
                    points = state.path,
                    color = Color.Blue,
                    width = 12f
                )
            }
        }

        LaunchedEffect(state.path.lastOrNull()) {
            state.path.lastOrNull()?.let {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(it, 18f)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Distance: ${(state.distance / 1000).format(2)} km", color = Color.White)
            Text("Time: ${state.duration / 1000}s", color = Color.White)

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                if (state.isRunning) viewModel.stopRun()
                else viewModel.startRun()
            }) {
                Text(if (state.isRunning) "Stop Run" else "Start Run")
            }
        }
    }
}

fun Float.format(digits: Int) = "%.${digits}f".format(this)
