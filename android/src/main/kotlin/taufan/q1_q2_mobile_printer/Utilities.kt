package taufan.q1_q2_mobile_printer

import android.graphics.Bitmap
import java.util.*
import kotlin.math.roundToInt


object Utilities {
    fun arrayListToIntList(list: ArrayList<Int>): IntArray {
        val ints = IntArray(list.size)
        for (i in list.indices) {
            ints[i] = list[i]
        }
        return ints
    }

    fun arrayListToString(list: ArrayList<String>): Array<String?> {
        val strings = arrayOfNulls<String>(list.size)
        for (i in list.indices) {
            strings[i] = list[i]
        }
        return strings
    }

    fun scaleDownBitmap(realImage: Bitmap, maxImageSize: Int,
                        filter: Boolean): Bitmap {

        realImage.setHasAlpha(true)
        val ratio: Float = (maxImageSize.toFloat() / realImage.width.toFloat()).coerceAtMost(maxImageSize.toFloat() / realImage.height.toFloat())
        val width = (ratio * realImage.width).roundToInt()
        val height = (ratio * realImage.height).roundToInt()

        return Bitmap.createScaledBitmap(realImage, width,
                height, filter)
    }
}
