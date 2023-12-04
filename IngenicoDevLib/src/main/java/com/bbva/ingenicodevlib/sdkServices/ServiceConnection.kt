package com.bbva.ingenicodevlib.sdkServices

import android.content.Context
import android.util.Log
import com.bbva.devicelib.connection.ISdkConnect

abstract class ServiceConnection: ISdkConnect {

    @Volatile
    var connected = false
        protected  set(value) {
            field = value
            Log.w(tag, "${if(field) "Connect" else "Disconnect"} $tag !")
        }

    @Volatile
    var binding = false
        protected set(value) {
            field = value
            Log.w(tag, "${if(field) "Binding" else "UnBinding"} $tag !")
        }

    abstract val tag: String

    protected abstract fun specificConnect(context: Context): Boolean
    protected abstract fun specificDisconnect(context: Context): Boolean

    @Synchronized
    override fun connect(context: Context): Boolean {
        return  if(!connected)
                specificConnect(context)
        else{
            Log.w(tag, "Connection is already established")
             true
        }
    }

    @Synchronized
    override fun disconnect(context: Context): Boolean {
       return if(connected)
           specificDisconnect(context)
        else{
            Log.w(tag, "Connection is already finished" )
           true
        }
    }

    override fun isConnected() = connected
}