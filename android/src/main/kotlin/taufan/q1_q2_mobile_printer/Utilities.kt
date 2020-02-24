package taufan.q1_q2_mobile_printer

import android.graphics.Bitmap
import java.util.*


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
        val ratio = Math.min(
                maxImageSize / realImage.width,
                maxImageSize / realImage.height)
        val width = Math.round((ratio * realImage.width).toDouble())
        val height = Math.round((ratio * realImage.height).toDouble())
        return Bitmap.createScaledBitmap(realImage, width.toInt(),
                height.toInt(), filter)
    }
}
