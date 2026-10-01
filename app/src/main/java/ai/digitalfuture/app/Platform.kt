package ai.digitalfuture.app

import androidx.annotation.DrawableRes

data class Platform(
    val id: String,
    val name: String,
    val subtitle: String,
    val url: String,
    @DrawableRes val logoRes: Int,
    val qrPayload: String = url
)

private val allDigitalFuturePlatforms = listOf(
    Platform(
        id = "nexora",
        name = "NEXORA-Digital",
        subtitle = "AI-Powered Website Builder",
        url = "https://www.nexoraonline.de/",
        logoRes = R.drawable.nexora_logo
    ),
    Platform(
        id = "sakan",
        name = "SAKAN",
        subtitle = "Connection & Family",
        url = "https://www.sakanapp.net/",
        logoRes = R.drawable.sakan_logo
    ),
    Platform(
        id = "helpme",
        name = "HELP-ME",
        subtitle = "Assistance & Guidance",
        url = "https://www.helpmey.net/",
        logoRes = R.drawable.help_me_logo
    )
)

/**
 * The Google Play product flavors are intentionally independent.
 * Each installed app exposes only its own platform while retaining
 * the common digital-future.ai identity.
 */
val digitalFuturePlatforms: List<Platform>
    get() = when (BuildConfig.FLAVOR.lowercase()) {
        "nexora" -> listOf(allDigitalFuturePlatforms[0])
        "sakan" -> listOf(allDigitalFuturePlatforms[1])
        "helpme" -> listOf(allDigitalFuturePlatforms[2])
        else -> allDigitalFuturePlatforms
    }

fun platformForIncomingUrl(url: String?): Platform? {
    val host = runCatching { android.net.Uri.parse(url).host }.getOrNull()?.lowercase() ?: return null
    return allDigitalFuturePlatforms.firstOrNull {
        android.net.Uri.parse(it.url).host?.lowercase() == host
    }
}
