package llp.lifeeasy.cricradio.data.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(65, 151, 254),
    primaryContainer = Color(0, 28, 56),
    onPrimary = Color(139, 190, 251),
    onPrimaryContainer = Color(226, 226, 226),
    secondary = Color(229, 148, 4),
    secondaryContainer = Color(58, 40, 9),
    onSecondary = Color(250, 200, 110),
    onSecondaryContainer = Color(226, 226, 226),
    surface = Color(0, 0, 0),
    onSurface = Color(255, 255, 255),
    surfaceVariant = Color(30, 30, 30),
    onSurfaceVariant = Color(206, 206, 206),
    background = Color(0, 0, 0),
    onBackground = Color(255, 255, 255),
)

@Composable
fun CricRadioTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
    MaterialTheme(DarkColorScheme, content = content)
}