package ro.pub.cs.systems.eim.practicaltest01var07



import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import kotlin.random.Random

class PracticalTest01Var07Service : Service() {

    private val handler = Handler()
    private lateinit var runnable: Runnable

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Define a runnable to generate and broadcast random values every 10 seconds
        runnable = Runnable {
            // Generate four random integers
            val value1 = Random.nextInt(0, 100)
            val value2 = Random.nextInt(0, 100)
            val value3 = Random.nextInt(0, 100)
            val value4 = Random.nextInt(0, 100)

            // Create an Intent to broadcast the values
            val broadcastIntent = Intent("com.example.practicaltest01var07.RANDOM_VALUES").apply {
                putExtra("value1", value1)
                putExtra("value2", value2)
                putExtra("value3", value3)
                putExtra("value4", value4)
            }
            sendBroadcast(broadcastIntent)

            // Schedule the runnable to run again in 10 seconds
            handler.postDelayed(runnable, 1000)
        }
        handler.post(runnable) // Start the runnable immediately

        return START_STICKY // Keep the service running until explicitly stopped
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable) // Stop the runnable when the service is destroyed
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // This is a started service, so no binding is provided
    }
}

