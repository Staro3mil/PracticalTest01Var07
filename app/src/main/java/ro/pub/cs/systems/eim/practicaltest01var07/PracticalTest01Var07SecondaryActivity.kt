package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_secondary)

        // Initialize TextViews
        val textView1 = findViewById<TextView>(R.id.textView1)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView4 = findViewById<TextView>(R.id.textView4)

        // Retrieve values from intent
        val value1 = intent.getIntExtra("value1", 0)
        val value2 = intent.getIntExtra("value2", 0)
        val value3 = intent.getIntExtra("value3", 0)
        val value4 = intent.getIntExtra("value4", 0)

        // Display values in TextViews
        textView1.text = value1.toString()
        textView2.text = value2.toString()
        textView3.text = value3.toString()
        textView4.text = value4.toString()

        // Sum and Product buttons
        val sumButton = findViewById<Button>(R.id.sumButton)
        val productButton = findViewById<Button>(R.id.productButton)

        // Set onClickListener for Sum button
        sumButton.setOnClickListener {
            val sum = value1 + value2 + value3 + value4
            returnResult(sum)
        }

        // Set onClickListener for Product button
        productButton.setOnClickListener {
            val product = value1 * value2 * value3 * value4
            returnResult(product)
        }
    }

    // Function to return the result to MainActivity
    private fun returnResult(result: Int) {
        val intent = Intent().apply {
            putExtra("result", result)
        }
        setResult(Activity.RESULT_OK, intent)
        finish() // Closes the secondary activity and returns to MainActivity
    }
}


