package ai.digitalfuture.app

data class Platform(
    val id: String,
    val name: String,
    val url: String,
    val qrPayload: String = url
)

val digitalFuturePlatforms = listOf(
    Platform("sakan", "SAKAN", "https://www.sakanapp.net"),
    Platform("nexora", "NEXORA", "https://www.nexoraonline.de"),
    Platform("help-me", "HELP-ME", "https://www.helpmey.net")
)
