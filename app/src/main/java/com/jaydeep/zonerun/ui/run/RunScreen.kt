package com.jaydeep.zonerun.ui.run

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaydeep.zonerun.run.RunViewModel

@Composable
fun RunScreen(viewModel: RunViewModel= viewModel()) {

    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, "Location permission is required", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Distance: ${(state.distance / 1000).format(2)} km")
        Text("Time: ${state.duration / 1000} sec")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (state.isRunning) viewModel.stopRun()
            else viewModel.startRun()
        }) {
            Text(if (state.isRunning) "Stop Run" else "Start Run")
        }
    }
}
fun Float.format(digits: Int) = "%.${digits}f".format(this)
