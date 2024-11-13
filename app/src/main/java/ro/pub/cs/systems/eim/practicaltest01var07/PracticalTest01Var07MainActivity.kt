package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    // Variables to hold the sum and product results
    private var sumResult: Int? = null
    private var productResult: Int? = null

    // Register for activity result to get the data back from SecondaryActivity
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val resultValue = data?.getIntExtra("result", 0)

            // Assuming that if a result is returned, it is either a sum or a product
            resultValue?.let {
                // Store result in sumResult or productResult based on the operation
                if (sumResult == null) {
                    sumResult = it
                    Toast.makeText(this, "Sum: $sumResult", Toast.LENGTH_LONG).show()
                } else {
                    productResult = it
                    Toast.makeText(this, "Product: $productResult", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        // Restore saved instance state if available
        savedInstanceState?.let {
            sumResult = it.getInt("SUM_RESULT")
            productResult = it.getInt("PRODUCT_RESULT")
        }

        // Show restored values if they exist
        sumResult?.let { Toast.makeText(this, "Restored Sum: $it", Toast.LENGTH_LONG).show() }
        productResult?.let { Toast.makeText(this, "Restored Product: $it", Toast.LENGTH_LONG).show() }

        // Initialize the EditText fields
        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val editText3 = findViewById<EditText>(R.id.editText3)
        val editText4 = findViewById<EditText>(R.id.editText4)

        // Set button functionality (validate input and navigate to next activity if valid)
        val setButton = findViewById<Button>(R.id.setButton)
        setButton.setOnClickListener {
            // Validate each field to check if it contains a valid integer
            val input1 = editText1.text.toString().toIntOrNull()
            val input2 = editText2.text.toString().toIntOrNull()
            val input3 = editText3.text.toString().toIntOrNull()
            val input4 = editText4.text.toString().toIntOrNull()

            // Check if all inputs are valid numbers
            if (input1 != null && input2 != null && input3 != null && input4 != null) {
                // All fields contain valid numbers, proceed with starting the secondary activity
                val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java).apply {
                    putExtra("value1", input1)
                    putExtra("value2", input2)
                    putExtra("value3", input3)
                    putExtra("value4", input4)
                }
                resultLauncher.launch(intent)
            } else {
                // At least one field is invalid, show a Toast message and ignore the click action
                Toast.makeText(this, "Please enter valid numbers in all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Save the sum and product results during configuration changes or activity destruction
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        sumResult?.let { outState.putInt("SUM_RESULT", it) }
        productResult?.let { outState.putInt("PRODUCT_RESULT", it) }
    }
}




