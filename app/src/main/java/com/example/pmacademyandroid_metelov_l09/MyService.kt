package com.example.pmacademyandroid_metelov_l09

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val tag: String = MyService::class.java.simpleName
    private val dollarToHryvniaRatio: Double = 28.60

    override fun onBind(intent: Intent?): IBinder? {
        return LocalBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "Service started")

        val stringValueUSD: String = intent?.getStringExtra("valueUSD").toString()

        val valueUAH: Double = convertUSDtoUAH(stringValueUSD)
        Log.d(tag, "UAH = $valueUAH")

        stopSelf()

        Intent().also { intent ->
            intent.action = R.string.BROADCAST_ACTION.toString()
            intent.putExtra("valueUAH", valueUAH)
            sendBroadcast(intent)
            Log.d(tag, "Intent sended")
            Log.d(tag, "extras: ${intent?.extras}")
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun convertUSDtoUAH(stringValueUSD: String): Double {
        Log.d(tag, "calculating data...")
        return stringValueUSD.toDouble() * dollarToHryvniaRatio
    }

    inner class LocalBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

}