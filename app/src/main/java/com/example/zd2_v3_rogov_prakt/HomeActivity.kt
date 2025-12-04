package com.example.zd2_v3_rogov_prakt

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    fun ToAdd(view: View){
        intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }
    fun ToList(view: View){
        intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}