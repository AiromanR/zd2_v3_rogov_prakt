package com.example.zd2_v3_rogov_prakt

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val pulseInput = findViewById<EditText>(R.id.email)
        val pressureInput = findViewById<EditText>(R.id.pressure)
        val temperatureInput = findViewById<EditText>(R.id.temperature)
        val addButton = findViewById<Button>(R.id.btnReg)

        addButton.setOnClickListener {
            val database = InfoDatabase.getDatabase(this)
            val pulse = pulseInput.text.toString()
            val pressure = pressureInput.text.toString()
            val temperature = temperatureInput.text.toString()

            if (pulse.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content),"Введите пульс",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val date = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date())

            val info = Info(
                pulse = pulse,
                pressure = pressure,
                temperature = temperature,
                date = date
            )

            database.infoDao().insert(info)

            Snackbar.make(findViewById(android.R.id.content),"Запись добавлена!",Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }
}