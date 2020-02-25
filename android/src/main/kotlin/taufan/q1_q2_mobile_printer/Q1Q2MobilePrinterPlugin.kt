package taufan.q1_q2_mobile_printer

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.util.*

/** Q1Q2MobilePrinterPlugin */
class Q1Q2MobilePrinterPlugin: FlutterPlugin, MethodCallHandler {

  private lateinit var printerCore: Q1Q2Core

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "q1_q2_mobile_printer")
    this.printerCore = Q1Q2Core(flutterPluginBinding.applicationContext)
    channel.setMethodCallHandler(this)
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "q1_q2_mobile_printer")

      var _theClass: Q1Q2MobilePrinterPlugin  = Q1Q2MobilePrinterPlugin()
      _theClass.printerCore = Q1Q2Core(registrar.activeContext())

      channel.setMethodCallHandler(_theClass)
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when (call.method) {
      "BIND_SERVICE" -> {
        this.printerCore.bindService()
        result.success(true)
      }
      "UNBIND_SERVICE" -> {
        this.printerCore.unbindService()
        result.success(true)
      }
      "PRINT_TEXT" -> {
        val text = call.argument<String>("text")
        this.printerCore.printText(text)
        result.success(true)
      }
      "SET_ALIGNMENT" -> {
        val alignment = call.argument<Int>("alignment")
        this.printerCore.setAlignment(alignment)
        result.success(true)
      }
      "SET_FONT_SIZE" -> {
        val fontSize = call.argument<Double>("font_size")
        this.printerCore.setFontSize(fontSize)
        result.success(true)
      }
      "SET_FONT_BOLD" -> {
        val fontBold = call.argument<Boolean>("font_bold")
        this.printerCore.setFontBold(fontBold)
        result.success(true)
      }
      "PRINT_COLUMN" -> {
        val stringColumn = call.argument<ArrayList<String>>("text_column")!!
        val columnWidth = call.argument<IntArray>("column_width")
        val columnAlignment = call.argument<IntArray>("column_alignment")
        val isLast: Boolean = call.argument<Boolean>("is_last")!!
        val strings: Array<String?> = Utilities.arrayListToString(stringColumn)

        this.printerCore.printColumn(strings, columnWidth, columnAlignment, isLast)
        result.success(true)
      }
      "LINE_FEED" -> {
        val lines = call.argument<Int>("lines")
        this.printerCore.lineFeed(lines)
        result.success(true)
      }
      "SET_FONT_TYPE" -> {
        val fontType = call.argument<String>("font_type")
        this.printerCore.setFontType(fontType)
        result.success(true)
      }
      "SET_FONT_EMPHASIZED" -> {
        val fontEmphasized = call.argument<Boolean>("font_emphasized")
        this.printerCore.setEmphasized(fontEmphasized)
        result.success(true)
      }
      "PRINT_IMAGE" -> {
        val imagePath = call.argument<String>("path")
        this.printerCore.printImage(imagePath)
        result.success(true)
      }
      "SEND_RAW" -> {
        val bytes = call.argument<ByteArray>("bytes")
        this.printerCore.sendRaw(bytes)
        result.success(true)
      }
      "BLANK_LINE" -> {
        this.printerCore.printBlankLine()
        result.success(true)
      }
      "COMMIT_PRINT" -> {
        this.printerCore.commit()
        result.success(true)
      }
      "START" -> {
        this.printerCore.start()
        result.success(true)
      }
      "CANCEL" -> {
        this.printerCore.cancel()
        result.success(true)
      }
      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }
}
