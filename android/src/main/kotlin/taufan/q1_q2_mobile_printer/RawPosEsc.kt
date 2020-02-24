package taufan.q1_q2_mobile_printer

object RawPosEsc {
    private const val LF: Byte = 10
    private const val ESC: Byte = 27
    private const val exclamation: Byte = 33
    private const val strip: Byte = 45
    private const val at: Byte = 64
    private const val E: Byte = 69
    private const val G: Byte = 71
    private const val M: Byte = 77
    private const val a: Byte = 97
    fun lineFeed(count: Int): ByteArray {
        val command = ByteArray(count)
        for (i in 0 until count) {
            command[i] = LF
        }
        return command
    }

    fun setFont(fontType: String?): ByteArray {
        val command = ByteArray(3)
        command[0] = ESC
        command[1] = M
        when (fontType) {
            "A" -> command[2] = 0
            "B" -> command[2] = 1
            "C" -> command[2] = 2
            else -> {
            }
        }
        return command
    }

    fun setEmphasized(emphasize: Boolean?): ByteArray {
        val command = ByteArray(3)
        command[0] = ESC
        command[1] = E
        if (emphasize!!) {
            command[2] = 1
        } else {
            command[2] = 0
        }
        return command
    }

    fun setBold(): ByteArray {
        return byteArrayOf(0x1B, 0x45, 0x1)
    }

    fun setUnBold(): ByteArray {
        return byteArrayOf(0x1B, 0x45, 0x0)
    }
}