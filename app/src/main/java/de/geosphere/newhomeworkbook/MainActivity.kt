package de.geosphere.newhomeworkbook

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import de.geosphere.newhomeworkbook.subject.SubjectMainPage
import de.geosphere.newhomeworkbook.ui.theme.NewHomeworkBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewHomeworkBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SubjectMainPage()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

// @Preview(showBackground = true)
@Preview(name = "Light Mode", showSystemUi = true, device = Devices.PIXEL_4_XL, showBackground = true)
@Preview(name = "Dark Mode", showSystemUi = true, device = Devices.PIXEL_4_XL, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    NewHomeworkBookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            SubjectMainPage()
        }
    }
}
