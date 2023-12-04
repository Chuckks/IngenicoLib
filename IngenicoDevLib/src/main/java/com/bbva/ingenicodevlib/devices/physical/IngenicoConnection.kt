package com.bbva.ingenicodevlib.devices.physical

import android.content.Context
import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.connection.ISdkConnect
import com.bbva.ingenicodevlib.sdkServices.PrinterService
import com.bbva.ingenicodevlib.sdkServices.SDKServices

private val TAG = Constant.DEV_PREFIX + IngenicoConnection::class.java.simpleName

class IngenicoConnection: ISdkConnect {

    private var connected: Boolean = false

    val printerServer = PrinterService()

    private val sdkService = SDKServices()

    init {
        sdkService.add(printerServer)
    }

    override fun isConnected() = connected

    override fun connect(context: Context): Boolean {
        sdkService.connect(context)
        connected = true
        Log.i(TAG, "Connecting Service...")
        return true
    }

    override fun disconnect(context: Context): Boolean {
        sdkService.disconnect(context)
        connected = false
        Log.i(TAG, "Disconnect Service...")
        return true
    }
}