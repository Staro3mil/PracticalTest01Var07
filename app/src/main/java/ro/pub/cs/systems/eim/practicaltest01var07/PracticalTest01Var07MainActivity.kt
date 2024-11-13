package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText

    // BroadcastReceiver to receive the random values from the service
    private val randomValuesReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Get the random values from the broadcast
            val value1 = intent.getIntExtra("value1", 0)
            val value2 = intent.getIntExtra("value2", 0)
            val value3 = intent.getIntExtra("value3", 0)
            val value4 = intent.getIntExtra("value4", 0)

            // Update the EditText fields with the received values
            editText1.setText(value1.toString())
            editText2.setText(value2.toString())
            editText3.setText(value3.toString())
            editText4.setText(value4.toString())

            // Optionally show a Toast with the received values
            Toast.makeText(context, "Received: $value1, $value2, $value3, $value4", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        // Initialize the EditText fields
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)

        // Start the service to generate random values every 10 seconds
        val serviceIntent = Intent(this, PracticalTest01Var07Service::class.java)
        startService(serviceIntent)

        // Register the receiver to listen for the random values broadcast
        // Register the broadcast receiver
        registerReceiver(randomValuesReceiver, IntentFilter("com.example.practicaltest01var07.RANDOM_VALUES"),
            RECEIVER_EXPORTED
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop the service when the activity is destroyed
        val serviceIntent = Intent(this, PracticalTest01Var07Service::class.java)
        stopService(serviceIntent)

        // Unregister the broadcast receiver
        unregisterReceiver(randomValuesReceiver)
    }
}
