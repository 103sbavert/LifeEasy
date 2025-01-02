package llp.lifeeasy.cricradio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import llp.lifeeasy.cricradio.data.KoinModules
import llp.lifeeasy.cricradio.data.MainViewModel
import llp.lifeeasy.cricradio.data.theme.CricRadioTheme
import org.koin.android.ext.koin.androidContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(KoinModules.module)
        }

        enableEdgeToEdge()
        setContent {
            val viewModel = koinViewModel<MainViewModel>()

            CricRadioTheme {
                Box(Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {
                    App(viewModel)
                }
            }
        }
    }
}