package com.bbva.ingenicodevlib.devices.physical

import android.content.Context
import com.bbva.devicelib.IDeviceCenter
import com.bbva.devicelib.IDeviceCenterCreator

class IngenicoDeviceCenterCreator: IDeviceCenterCreator {
    override fun create(context: Context) = IngenicoDeviceCenter(context)
}