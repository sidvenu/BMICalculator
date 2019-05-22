package io.github.sidvenu.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val green: Int = resources.getColor(R.color.colorGreen)
        val red: Int = resources.getColor(R.color.colorRed)
        val yellow: Int = resources.getColor(R.color.colorYellow)

        val weightInput = findViewById<TextInputEditText>(R.id.weight_input)
        val heightInput = findViewById<TextInputEditText>(R.id.height_input)
        weightInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        findViewById<Button>(R.id.bmi_calculate).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                weightInput.error = null
                heightInput.error = null

                val weight: String = weightInput.text.toString()
                val height: String = heightInput.text.toString()

                if (!isNumeric(weight))
                    weightInput.error = "Weight needs to be a real number"
                if (!isNumeric(height))
                    heightInput.error = "Height needs to be a real number"
                if (isNumeric(weight) && isNumeric(height)) {
                    val bmi: Double = weight.toDouble() / (height.toDouble() * height.toDouble())
                    val background: Int
                    background = if (bmi < 21.7)
                        ColorUtils.blendARGB(green, yellow, 1-(bmi / 21.7).toFloat())
                    else
                        ColorUtils.blendARGB(green, red, 1-(21.7 / bmi).toFloat())
                    findViewById<LinearLayout>(R.id.root_view).setBackgroundColor(background)
                    findViewById<TextView>(R.id.bmi_display).text = String.format("%.2f",bmi.toFloat())
                }
            }
        })
    }

    fun isNumeric(str: String): Boolean {
        return str.matches("-?\\d+(\\.\\d+)?".toRegex())  //match a number with optional '-' and decimal.
    }
}
