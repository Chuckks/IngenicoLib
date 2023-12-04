package com.bbva.ingenicodevlib.sdkServices

import android.content.Context
import android.util.Log
import com.bbva.devicelib.Constant
import com.bbva.devicelib.connection.ISdkConnect

private val TAG = Constant.DEV_PREFIX + SDKServices::class.java.simpleName

class SDKServices: ISdkConnect  {
    private var connected = false
    private var serviceList = mutableListOf<ServiceConnection>()

    fun add( service: ServiceConnection ) = serviceList.add(service)

    override fun connect(context: Context): Boolean {
        serviceList.forEach { service ->
            val binding = service.connect(context)
            Log.i(TAG, "Service Connect [${service}] Binding -> [$binding]")
        }
        connected = true
        return true
    }

    override fun disconnect(context: Context): Boolean {
        serviceList.forEach { service ->
            val binding = service.disconnect(context)
            Log.i(TAG, "Service Disconnect [${service.tag}] Binding -> [$binding]")
        }

        connected = false
        return true
    }

    override fun isConnected() = connected
}