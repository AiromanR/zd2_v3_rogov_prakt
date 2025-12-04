package com.example.zd2_v3_rogov_prakt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    private var selectedTemperature: Float = 36.6f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val pulseInput = findViewById<EditText>(R.id.email)
        val pressureInput = findViewById<EditText>(R.id.pressure)
        val temperatureSlider = findViewById<SeekBar>(R.id.temperatureSlider)
        val temperatureValueText = findViewById<TextView>(R.id.temperatureValue)
        val addButton = findViewById<Button>(R.id.btnReg)

        temperatureSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                selectedTemperature = 34.0f + (progress * 0.1f)
                temperatureValueText.text = String.format("%.1f", selectedTemperature)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        addButton.setOnClickListener {
            val database = InfoDatabase.getDatabase(this)
            val pulse = pulseInput.text.toString()
            val pressure = pressureInput.text.toString()

            if (pulse.isEmpty()) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Введите пульс",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val date = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date())

            val info = Info(
                pulse = pulse,
                pressure = pressure,
                temperature = String.format("%.1f", selectedTemperature),
                date = date
            )

            database.infoDao().insert(info)

            Snackbar.make(
                findViewById(android.R.id.content),
                "Запись добавлена!",
                Snackbar.LENGTH_SHORT
            ).show()
            finish()
        }
    }
}