package ai.digitalfuture.app

import androidx.annotation.DrawableRes
import ai.digitalfuture.app.R

data class Platform(
    val id: String,
    val name: String,
    val url: String,
    @DrawableRes val logoRes: Int,
    val qrPayload: String = url
)

val digitalFuturePlatforms = listOf(
    Platform("sakan", "SAKAN", "https://www.sakanapp.net", R.drawable.sakan_logo),
    Platform("nexora", "NEXORA", "https://www.nexoraonline.de", R.drawable.nexora_logo),
    Platform("help-me", "HELP-ME", "https://www.helpmey.net", R.drawable.help_me_logo)
)
