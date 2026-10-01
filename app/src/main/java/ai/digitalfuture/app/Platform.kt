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
