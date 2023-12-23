package de.geosphere.newhomeworkbook.subject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import de.geosphere.newhomeworkbook.R
import de.geosphere.newhomeworkbook.ui.theme.DayNightPreviews
import de.geosphere.newhomeworkbook.ui.theme.NewHomeworkBookTheme

@Composable
fun SubjectOverview() {
    Column(modifier = Modifier) {
        // DummyImage
        Image(
            modifier = Modifier.fillMaxWidth().size(206.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.dummy_comp_overview_graph),
            contentDescription = "dummy",
        )
        Text(
            text = "Offene Aufgaben",
            style = MaterialTheme.typography.bodyLarge,
        )
        TabRow(selectedTabIndex = 0) {
            Tab(
                selected = true,
                onClick = { /*TODO*/ },
                text = { Text(text = "Prüfung") },
            )
            Tab(
                selected = false,
                onClick = { /*TODO*/ },
                text = { Text(text = "Hausaufgaben") },
            )
            Tab(
                selected = false,
                onClick = { /*TODO*/ },
                text = { Text(text = "Sonstiges") },
            )
        }
        Spacer(modifier = Modifier.size(100.dp))

        Text(
            text = "Erledigte Aufgaben",
            style = MaterialTheme.typography.bodyLarge,
        )
        TabRow(selectedTabIndex = 1) {
            Tab(
                selected = false,
                onClick = { /*TODO*/ },
                text = { Text(text = "Prüfung") },
            )
            Tab(
                selected = true,
                onClick = { /*TODO*/ },
                text = { Text(text = "Hausaufgaben") },
            )
            Tab(
                selected = false,
                onClick = { /*TODO*/ },
                text = { Text(text = "Sonstiges") },
            )
        }
        Spacer(modifier = Modifier.size(100.dp))
    }
}

@Composable
@DayNightPreviews
fun SubjectOverview_Preview() {
    NewHomeworkBookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            SubjectOverview()
        }
    }
}
