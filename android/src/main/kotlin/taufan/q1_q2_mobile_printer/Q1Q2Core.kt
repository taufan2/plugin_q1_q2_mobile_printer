package taufan.q1_q2_mobile_printer

import android.R.attr.bitmap
import android.R.attr.opacity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.*
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast
import androidx.annotation.NonNull
import com.iposprinter.iposprinterservice.IPosPrinterCallback
import com.iposprinter.iposprinterservice.IPosPrinterService


class Q1Q2Core constructor(appContext: Context) {
    var TAG: String = "Q1Q2Core"

    private val context: Context = appContext

    private lateinit var printerService: IPosPrinterService

    private val connService: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(component: ComponentName?) {
            TODO("belum di implementasi") // Callback jika service connection terputus
        }

        override fun onServiceConnected(component: ComponentName?, service: IBinder?) {
            printerService = IPosPrinterService.Stub.asInterface(service)
            Toast.makeText(context, "Service Connected.", Toast.LENGTH_LONG).show()
        }

    }

    private fun callback(): IPosPrinterCallback {
        return object : IPosPrinterCallback.Stub() {
            @Throws(RemoteException::class)
            override fun onRunResult(isSuccess: Boolean) {

            }

            @Throws(RemoteException::class)
            override fun onReturnString(value: String) {

            }
        }
    }

    fun bindService() {
        var intent: Intent = Intent()
        intent.setPackage("com.iposprinter.iposprinterservice")
        intent.action = "com.iposprinter.iposprinterservice.IPosPrintService"
        context.bindService(intent, connService, Context.BIND_AUTO_CREATE)
    }

    fun unbindService() {
        context.unbindService(connService)
    }

    fun printText(@NonNull string: String? = "") {
        printerService.printText(string, this.callback())
    }

    fun setAlignment(@NonNull alignment: Int?) {
        printerService.setPrinterPrintAlignment(alignment!!, this.callback())
    }

    fun setFontSize(@NonNull fontSize: Double?) {
        printerService.setPrinterPrintFontSize(fontSize!!.toInt(), callback())
    }

    fun setFontBold(@NonNull fontBold: Boolean? = false) {
        val command: ByteArray = if (fontBold!!) RawPosEsc.setBold() else RawPosEsc.setUnBold()
        printerService.printRawData(command, callback())
    }

    fun printColumn(strings: Array<String?>, columnWidth: IntArray?, columnAlignment: IntArray?, isLast: Boolean) {
        /* width column (384 / font_size(default: 24)) - (total_kolom(default: 2)+1)*/
        printerService.printColumnsText(strings, columnWidth, columnAlignment, if (isLast) 0 else 1, callback())
    }

    fun lineFeed(lines: Int? = 1) {
        printerService.printerFeedLines(lines!!, callback())
    }

    fun setFontType(fontType: String?) {
        printerService.setPrinterPrintFontType(fontType, callback())
    }

    fun setEmphasized(fontEmphasized: Boolean? = true) {
        val command: ByteArray = RawPosEsc.setEmphasized(fontEmphasized)
        printerService.printRawData(command, callback())
    }

    fun printImage(imagePath: String?) {
        val bmOptions = BitmapFactory.Options()
        val bitmapFactory = BitmapFactory.decodeFile(imagePath, bmOptions)
        var bitmapCanvas = bitmapFactory.copy(Bitmap.Config.ARGB_8888, true)

        var canvas: Canvas = Canvas(bitmapCanvas)

        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmapFactory, 0.toFloat(), 0.toFloat(), null)

        val scaledBitmap = Utilities.scaleDownBitmap(bitmapCanvas, 380, true)

        printerService.printBitmap(1, 12, scaledBitmap, callback())
        printerService.printBlankLines(1, 16, callback())
    }

    fun sendRaw(bytes: ByteArray?) {
        printerService.printRawData(bytes, callback())
    }

    fun commit() {
        printerService.printerPerformPrint(140, callback())
    }

    fun start() {
        printerService.printerInit(callback())
    }

    fun printBlankLine() {
        printerService.printBlankLines(1, 16, callback())
    }

    fun cancel() {

    }

}