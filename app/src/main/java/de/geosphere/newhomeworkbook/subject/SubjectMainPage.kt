package de.geosphere.newhomeworkbook.subject

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.geosphere.newhomeworkbook.ui.theme.Ueberschrift00

@Preview(
    name = "Day theme",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night theme",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class DayNightPreviews

@Composable
fun SubjectMainPage() {
    Column(modifier = Modifier) {
        Text(
            text = "Mathematik",
            textAlign = TextAlign.Center,
            fontSize = 45.sp,
            textDecoration = TextDecoration.None,
            letterSpacing = 0.sp,
            lineHeight = 52.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
            color = Ueberschrift00,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        )
        Row {
            Column {
                Text(
                    text = "Lehrkraft: Joe Dode",
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.25.sp,
                    lineHeight = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(127.dp)
                        // .height(20.dp)
                        .alpha(1f),
                    color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
                Text(
                    text = "Lehrkraft: Joe Dode",
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.25.sp,
                    lineHeight = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(127.dp)
                        // .height(20.dp)
                        .alpha(1f),
                    color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
        var tabIndex by remember { mutableStateOf(0) }

        val tabs = listOf("Home", "About", "Settings")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                    )
                }
            }
//            when (tabIndex) {
//                0 -> HomeScreen()
//                1 -> AboutScreen()
//                2 -> SettingsScreen()
//            }
        }
    }
}

@Composable
@DayNightPreviews
fun SubjectMainPage_Preview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        SubjectMainPage()
    }
}
