package com.bbva.ingenicodevlib

import android.app.Application
import android.bluetooth.BluetoothClass.Device
import android.util.Log
import com.bbva.devicelib.IDeviceCenter

private val TAG = App::class.java.simpleName
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate Application")
        deviceCenter =  DeviceCenterFactory().create(DeviceCenterFactory.EDevType.INGENICO, applicationContext)
        deviceCenter.connect()
    }

    companion object{
        lateinit var deviceCenter: IDeviceCenter
    }
}