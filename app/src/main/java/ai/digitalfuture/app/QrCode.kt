package ai.digitalfuture.app

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

fun createQrCode(payload: String, size: Int = 512): Bitmap {
    val matrix: BitMatrix = MultiFormatWriter().encode(
        payload,
        BarcodeFormat.QR_CODE,
        size,
        size
    )

    val pixels = IntArray(size * size)
    for (y in 0 until size) {
        for (x in 0 until size) {
            pixels[y * size + x] = if (matrix[x, y]) {
                android.graphics.Color.BLACK
            } else {
                android.graphics.Color.WHITE
            }
        }
    }

    return Bitmap.createBitmap(
        pixels,
        size,
        size,
        Bitmap.Config.ARGB_8888
    )
}
