package com.bbva.ingenicodevlib.demo

import com.bbva.devicelib.physical.IPrinter
import com.bbva.ingenicodevlib.App

class Printer {

    operator fun invoke() {
        val printer = App.deviceCenter.printer

        printer.align = IPrinter.EAlign.CENTER
        printer.font = IPrinter.EFont.SMALL
        printer.text("Center con fuente SMALL")
        printer.lineFeed(1)

        printer.font = IPrinter.EFont.NORMAL
        printer.align = IPrinter.EAlign.LEFT
        printer.text("izquierda con fuente NORMAL")
        printer.lineFeed(1)

        printer.font = IPrinter.EFont.MEDIUM
        printer.align = IPrinter.EAlign.RIGHT
        printer.text("Derecha con fuente MEDIUM")
        printer.lineFeed(1)

        printer.font = IPrinter.EFont.BIG
        printer.align = IPrinter.EAlign.CENTER
        printer.text("Center con fuente BIG")
        printer.lineFeed(1)
        /*printer.qrCode("www.google.com", 8)
        printer.lineFeed(2)
        printer.barCode("12345678890", IPrinter.EBarCode.CODE39, 100, 10, true)
        printer.lineFeed(2)
        printer.bold = true
        printer.underline = true
        */
        //printer.image(resource.getAsset("test.jpg"))
        //printer.image(this, R.drawable.test)
    }
}