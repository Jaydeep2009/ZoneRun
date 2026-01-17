import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jaydeep.zonerun.ui.run.RunScreen

@Composable
fun ZoneRunNavGraph(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "run") {

        composable("run") {
            RunScreen()
        }

        composable("profile") {
            ProfileScreen(onLogout = onLogout)
        }
    }
}

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    TODO("Not yet implemented")
}

annotation class ZoneRunNavGraph
