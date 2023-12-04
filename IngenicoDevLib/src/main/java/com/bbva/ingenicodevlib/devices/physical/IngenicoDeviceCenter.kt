package com.bbva.ingenicodevlib.devices.physical

import android.content.Context
import com.bbva.devicelib.IDeviceCenter
import com.bbva.devicelib.physical.IPrinter
import com.bbva.ingenicodevlib.physical.Printer

class IngenicoDeviceCenter(private val context: Context): IDeviceCenter {
    val ingenico = IngenicoConnection()

    override val printer: IPrinter = Printer(ingenico.printerServer)

    override fun isConnected() = ingenico.isConnected()
    override fun connect() = ingenico.connect(context)
    override fun disconnect() = ingenico.disconnect(context)
}