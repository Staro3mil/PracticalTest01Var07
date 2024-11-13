package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import kotlin.random.Random

class PracticalTest01Var07Service : Service() {

    private val handler = Handler(Looper.getMainLooper())
    private val randomBroadcastRunnable = object : Runnable {
        override fun run() {
            // Generate four random integers
            val randomValues = IntArray(4) { Random.nextInt(0, 100) }

            // Create an intent to broadcast these values
            val broadcastIntent = Intent("com.example.practicaltest01var07.RANDOM_VALUES")
            broadcastIntent.putExtra("value1", randomValues[0])
            broadcastIntent.putExtra("value2", randomValues[1])
            broadcastIntent.putExtra("value3", randomValues[2])
            broadcastIntent.putExtra("value4", randomValues[3])

            // Send the broadcast
            sendBroadcast(broadcastIntent)

            // Schedule the next broadcast in 10 seconds
            handler.postDelayed(this, 10000)
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Start broadcasting random values every 10 seconds
        handler.post(randomBroadcastRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop broadcasting when the service is destroyed
        handler.removeCallbacks(randomBroadcastRunnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        // This is a started service, so binding is not used
        return null
    }
}
