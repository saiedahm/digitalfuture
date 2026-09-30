package ai.digitalfuture.app

import androidx.annotation.DrawableRes
import ai.digitalfuture.app.R

data class Platform(
    val id: String,
    val name: String,
    val subtitle: String,
    val url: String,
    @DrawableRes val logoRes: Int,
    val qrPayload: String = url
)

val digitalFuturePlatforms = listOf(
    Platform(
        id = "sakan",
        name = "SAKAN",
        subtitle = "Connection, marriage and family — 18+",
        url = "https://www.sakanapp.net",
        logoRes = R.drawable.sakan_logo
    ),
    Platform(
        id = "help-me",
        name = "HELP-ME",
        subtitle = "Assistance and guidance for unfamiliar situations",
        url = "https://www.helpmey.net",
        logoRes = R.drawable.help_me_logo
    ),
    Platform(
        id = "nexora",
        name = "NEXORA DIGITAL",
        subtitle = "AI + Web + Automation",
        url = "https://www.nexoraonline.de",
        logoRes = R.drawable.nexora_logo
    )
)
