package com.bbva.ingenicodevlib

import android.content.Context
import com.bbva.devicelib.IDeviceCenter
import com.bbva.ingenicodevlib.devices.physical.IngenicoDeviceCenterCreator

class DeviceCenterFactory {

    enum class EDevType {
        INGENICO,
        VERIFONE,
        SUNMI,
        FAKE
    }

    fun create(type: EDevType, context: Context): IDeviceCenter {
        return when (type) {
            EDevType.INGENICO -> IngenicoDeviceCenterCreator().create(context)
           // EDevType.VERIFONE -> VerifoneDeviceCenterCreator().create(context)
            //  EDevType.FAKE  -> FakeDeviceCenterCreator().create(context)

            else           -> {
                throw Exception("No yet Implemented [${type.name}]")
            }
        }
    }
}