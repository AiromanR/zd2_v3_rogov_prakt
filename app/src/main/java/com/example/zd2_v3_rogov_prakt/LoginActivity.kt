package com.example.zd2_v3_rogov_prakt

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById(R.id.email)
        passwordInput = findViewById(R.id.password)

        sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
    }

    fun Reg(view: View) {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content),"Заполните все поля",Snackbar.LENGTH_SHORT).show()
            return
        }

        if (password.length < 8) {
            Snackbar.make(findViewById(android.R.id.content),"Пароль минимум 8 символов",Snackbar.LENGTH_SHORT).show()
            return
        }

        val savedEmail = sharedPref.getString("email_$email", "")
        if (savedEmail != "") {
            Snackbar.make(findViewById(android.R.id.content),"Этот email уже зарегистрирован",Snackbar.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPref.edit()
        editor.putString("email_$email", email)
        editor.putString("pass_$email", password)
        editor.apply()

        Snackbar.make(findViewById(android.R.id.content),"Регистрация успешна!",Snackbar.LENGTH_SHORT).show()

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    fun Sign(view: View) {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content),"Заполните все поля",Snackbar.LENGTH_SHORT).show()
            return
        }

        val savedPassword = sharedPref.getString("pass_$email", "")

        if (savedPassword == password && savedPassword != "") {
            Snackbar.make(findViewById(android.R.id.content),"Вход выполнен!",Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            Snackbar.make(findViewById(android.R.id.content),"Неверный email или пароль",Snackbar.LENGTH_SHORT).show()
        }
    }
}