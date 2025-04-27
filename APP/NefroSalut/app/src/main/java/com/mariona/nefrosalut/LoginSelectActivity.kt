package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_select)
        val btnPacient = findViewById<Button>(R.id.btnSubmitRegister2)
        val btnFamiliar = findViewById<Button>(R.id.btnSubmitRegister4)

        btnPacient.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnFamiliar.setOnClickListener {
            val intent = Intent(this, LoginFamiliar::class.java)
            startActivity(intent)
        }
    }
}