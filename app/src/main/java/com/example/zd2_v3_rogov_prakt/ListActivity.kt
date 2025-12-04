package com.example.zd2_v3_rogov_prakt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class ListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var database: InfoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listView = findViewById(R.id.listView)
        database = InfoDatabase.getDatabase(this)

        showInfos()
    }

    private fun showInfos() {
        val infos = database.infoDao().getAll()

        val adapter = object : BaseAdapter() {
            override fun getCount(): Int = infos.size
            override fun getItem(position: Int): Any = infos[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, view: View?, parent: ViewGroup): View {

                val itemView = view ?: LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_task, parent, false)

                val info = infos[position]

                val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
                val tvDesc = itemView.findViewById<TextView>(R.id.tvDescription)
                val tvTemp = itemView.findViewById<TextView>(R.id.tvTemperature)
                val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
                val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)

                tvTitle.text = "Пульс: ${info.pulse} уд/мин"
                tvDesc.text = "Давление: ${info.pressure}"
                tvTemp.text = "Температура: ${info.temperature}°C"
                tvDate.text = info.date

                btnDelete.setOnClickListener {
                    database.infoDao().delete(info)
                    Snackbar.make(findViewById(android.R.id.content),"Запись удалена!",Snackbar.LENGTH_SHORT).show()
                    showInfos()
                }

                return itemView
            }
        }

        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        showInfos()
    }
}