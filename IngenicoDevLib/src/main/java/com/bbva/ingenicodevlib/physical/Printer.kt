package com.bbva.ingenicodevlib.physical

import android.graphics.Bitmap
import android.os.Binder
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.physical.IPrinter
import com.bbva.ingenicodevlib.sdkServices.PrinterService
import com.usdk.apiservice.aidl.printer.ASCSize
import com.usdk.apiservice.aidl.printer.OnPrintListener
import com.usdk.apiservice.aidl.printer.UPrinter
import java.io.ByteArrayOutputStream


private val TAG = Constant.DEV_PREFIX + Printer::class.java.simpleName

class Printer( private val service: PrinterService) : IPrinter {

    private val device: UPrinter
        get() = service.device

    private fun startPrint(){
        device.startPrint(printCallback)
        reset()
    }

    private fun getTranslateFont(font: IPrinter.EFont): Int{
        return when(font) {
            IPrinter.EFont.SMALL -> ASCSize.DOT7x7
            IPrinter.EFont.NORMAL -> ASCSize.DOT16x8
            IPrinter.EFont.MEDIUM -> ASCSize.DOT24x12
            IPrinter.EFont.BIG ->  ASCSize.DOT32x12
        }
    }

    private val printCallback: OnPrintListener.Stub =
        object : OnPrintListener.Stub() {
            @Throws(RemoteException::class)
            override fun onFinish() { Log.i(TAG, "The Printer has Finished...") }

            @Throws(RemoteException::class)
            override fun onError(error: Int) { Log.i(TAG, "Failed to Print...") }
        }

    override var bold: Boolean = false
    override var underline: Boolean = false

    override var align: IPrinter.EAlign = IPrinter.EAlign.CENTER

    override var font: IPrinter.EFont = IPrinter.EFont.NORMAL
        set(value) {
            device.setAscSize(getTranslateFont(value))
            field = value
        }

    override fun command(data: ByteArray) {}

    override fun init() {
        reset()
    }

    override fun reset() = device.clear()

    override fun lineFeed(num: Int) {
        device.feedLine(num)
        startPrint()
    }

    override fun text(text: String) {
        device.addText(align.ordinal, text)
        startPrint()
    }

    override fun textLine(text: String) {
        device.addText(align.ordinal, text)
        device.feedLine(1)
        startPrint()
    }

    override fun format(font: IPrinter.EFont, bold: Boolean, align: IPrinter.EAlign) {
        this.font = font
        this.bold = bold
        this.align = align
    }

    override fun barCode(data: String, typeBarcode: IPrinter.EBarCode, height: Int, width: Int, showText: Boolean) {
        device.addBarCode(align.ordinal, height, width, data)
        startPrint()
    }

    override fun qrCode(data: String, size: Int) {
        device.addQrCode(size,size,size, data)
        startPrint()
    }

    override fun image(value: ByteArray) {
        device.addImage(align.ordinal, value)
        startPrint()
    }

    override fun image(bitmap: Bitmap) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        image(outputStream.toByteArray())
    }

    override fun isAvailable() = device.status == 0
}