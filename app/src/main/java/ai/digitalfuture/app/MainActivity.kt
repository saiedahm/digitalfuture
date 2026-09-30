package ai.digitalfuture.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

private val DeepNavy = Color(0xFF020817)
private val Navy = Color(0xFF07152D)
private val ElectricBlue = Color(0xFF00B7FF)
private val Gold = Color(0xFFFFC43D)
private val SoftWhite = Color(0xFFF5F9FF)
private val Muted = Color(0xFFB8C7DD)

@Composable
private fun DigitalFutureBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(DeepNavy, Color(0xFF061B38), DeepNavy)
                )
            )
    ) {
        content()
    }
}

@Composable
private fun PlatformCard(
    platform: Platform,
    onOpen: () -> Unit,
    onQr: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(listOf(ElectricBlue.copy(alpha = 0.55f), Gold.copy(alpha = 0.55f))),
                shape = RoundedCornerShape(24.dp)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Navy.copy(alpha = 0.94f))
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(86.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.Black.copy(alpha = 0.28f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(platform.logoRes),
                        contentDescription = "${platform.name} official logo",
                        modifier = Modifier.size(82.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        platform.name,
                        color = SoftWhite,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        platform.subtitle,
                        color = Muted,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Text(
                        platform.url,
                        color = ElectricBlue,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onOpen,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricBlue,
                        contentColor = DeepNavy
                    )
                ) {
                    Text("Open platform", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onQr,
                    modifier = Modifier.weight(0.78f),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gold,
                        contentColor = DeepNavy
                    )
                ) {
                    Text("QR access", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DigitalFutureApp(initialUrl: String?) {
    val context = LocalContext.current
    var qrPlatform by remember { mutableStateOf<Platform?>(null) }
    val openedPlatform = remember(initialUrl) { platformForUrl(initialUrl) }

    MaterialTheme {
        DigitalFutureBackground {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "digital-future.ai",
                                color = SoftWhite,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = DeepNavy.copy(alpha = 0.96f),
                            titleContentColor = SoftWhite
                        )
                    )
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(28.dp),
                            color = Color.Transparent,
                            tonalElevation = 0.dp
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(28.dp))
                                    .background(
                                        Brush.linearGradient(
                                            listOf(
                                                Color(0xFF082A55),
                                                Color(0xFF071426),
                                                Color(0xFF18233A)
                                            )
                                        )
                                    )
                                    .border(
                                        1.dp,
                                        Brush.horizontalGradient(
                                            listOf(ElectricBlue.copy(alpha = 0.75f), Gold.copy(alpha = 0.75f))
                                        ),
                                        RoundedCornerShape(28.dp)
                                    )
                                    .padding(22.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.digital_future_logo),
                                    contentDescription = "digital-future.ai official logo",
                                    modifier = Modifier.size(170.dp)
                                )
                                Text(
                                    "Digital Future AI",
                                    color = SoftWhite,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 6.dp)
                                )
                                Text(
                                    "One ecosystem. Three specialized digital platforms.",
                                    color = Muted,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(top = 6.dp)
                                )
                                Text(
                                    "AI • DIGITAL TECHNOLOGY • MODERN SOLUTIONS",
                                    color = Gold,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 12.dp)
                                )
                                openedPlatform?.let {
                                    Text(
                                        "Opened from ${it.name}",
                                        color = ElectricBlue,
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            "Our platforms",
                            color = SoftWhite,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
                        )
                    }

                    items(digitalFuturePlatforms) { platform ->
                        PlatformCard(
                            platform = platform,
                            onOpen = {
                                context.startActivity(
                                    Intent(Intent.ACTION_VIEW, Uri.parse(platform.url))
                                )
                            },
                            onQr = { qrPlatform = platform }
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Official digital-future.ai ecosystem",
                                color = Muted,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                "Digital Future AI • SAKAN • HELP-ME • NEXORA DIGITAL",
                                color = ElectricBlue,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        qrPlatform?.let { platform ->
            val bitmap = remember(platform.qrPayload) { createQrCode(platform.qrPayload) }
            AlertDialog(
                onDismissRequest = { qrPlatform = null },
                containerColor = Navy,
                titleContentColor = SoftWhite,
                textContentColor = Muted,
                title = { Text("${platform.name} — QR access", fontWeight = FontWeight.Bold) },
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White)
                                .padding(14.dp)
                        ) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "QR code for ${platform.name}",
                                modifier = Modifier.size(250.dp)
                            )
                        }
                        Text(
                            platform.url,
                            color = ElectricBlue,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 14.dp)
                        )
                        Text(
                            "Scan this code to open the official platform.",
                            color = Muted,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { qrPlatform = null }) {
                        Text("Close", color = Gold, fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    }
}
