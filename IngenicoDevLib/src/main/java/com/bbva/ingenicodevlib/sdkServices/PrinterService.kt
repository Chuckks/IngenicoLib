package com.bbva.ingenicodevlib.sdkServices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.app.Service
import android.os.Binder
import com.usdk.apiservice.aidl.UDeviceService
import com.bbva.devicelib.Constant
import com.usdk.apiservice.aidl.printer.UPrinter


class PrinterService: ServiceConnection() {

    override val tag: String = Constant.DEV_PREFIX + PrinterService::class.java.simpleName

    private var deviceService: UDeviceService? = null
    lateinit var device: UPrinter

    private val bindCallback: android.content.ServiceConnection =
        object: android.content.ServiceConnection {

            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                deviceService = UDeviceService.Stub.asInterface(service)
                deviceService?.register(null, Binder())
                device = UPrinter.Stub.asInterface(deviceService?.printer)
                connected = true
            }

            override fun onServiceDisconnected(name: ComponentName) {
                connected = false
            }
        }
    
    override fun specificConnect(context: Context): Boolean {
        val intent = Intent("com.usdk.apiservice")
        intent.setPackage("com.usdk.apiservice")
        binding = context.bindService(intent, bindCallback, Service.BIND_AUTO_CREATE)
        return binding
    }

    override fun specificDisconnect(context: Context): Boolean {
        context.unbindService(bindCallback)
        connected = false
        binding = false
        return true
    }
}
