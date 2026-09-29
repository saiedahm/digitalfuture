package ai.digitalfuture.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private var incomingUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        incomingUrl = intent?.dataString
        setContent { DigitalFutureApp(initialUrl = incomingUrl) }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        incomingUrl = intent.dataString
    }
}

private fun platformForUrl(url: String?): Platform? {
    val host = runCatching { Uri.parse(url).host }.getOrNull()?.lowercase() ?: return null
    return digitalFuturePlatforms.firstOrNull { Uri.parse(it.url).host == host }
}

@Composable
private fun DigitalFutureApp(initialUrl: String?) {
    val context = LocalContext.current
    var qrPlatform by remember { mutableStateOf<Platform?>(null) }
    val openedPlatform = remember(initialUrl) { platformForUrl(initialUrl) }

    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("digital-future.ai") }) }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Column(modifier = Modifier.padding(bottom = 4.dp)) {
                        Image(
                            painter = painterResource(R.drawable.digital_future_logo),
                            contentDescription = "digital-future.ai logo",
                            modifier = Modifier.size(150.dp)
                        )
                        Text("digital-future.ai", style = MaterialTheme.typography.headlineMedium)
                        Text(
                            "SAKAN · NEXORA · HELP-ME",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        openedPlatform?.let {
                            Text(
                                "Opened from ${it.name}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                items(digitalFuturePlatforms) { platform ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Image(
                                    painter = painterResource(platform.logoRes),
                                    contentDescription = "${platform.name} logo",
                                    modifier = Modifier.size(76.dp)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(platform.name, style = MaterialTheme.typography.titleLarge)
                                    Text(
                                        platform.url,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                            Button(
                                onClick = {
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(platform.url)))
                                },
                                modifier = Modifier.fillMaxWidth().padding(top = 14.dp)
                            ) { Text("Open ${platform.name}") }
                            TextButton(
                                onClick = { qrPlatform = platform },
                                modifier = Modifier.fillMaxWidth()
                            ) { Text("Show QR Code") }
                        }
                    }
                }
            }
        }

        qrPlatform?.let { platform ->
            val bitmap = remember(platform.url) { createQrCode(platform.qrPayload) }
            AlertDialog(
                onDismissRequest = { qrPlatform = null },
                title = { Text("${platform.name} QR Code") },
                text = {
                    Column {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "QR Code for ${platform.name}",
                            modifier = Modifier.size(260.dp)
                        )
                        Text(
                            platform.url,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { qrPlatform = null }) { Text("Close") }
                }
            )
        }
    }
}
