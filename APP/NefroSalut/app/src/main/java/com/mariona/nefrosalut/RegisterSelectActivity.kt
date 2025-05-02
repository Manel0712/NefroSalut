package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registre_personal)

        val btnPacient = findViewById<Button>(R.id.btnSubmitRegister2)
        val btnFamiliar = findViewById<Button>(R.id.btnSubmitRegister4)

        btnPacient.setOnClickListener {
            val intent = Intent(this, RegisterPacientActivity::class.java)
            startActivity(intent)
        }

        btnFamiliar.setOnClickListener {
            val intent = Intent(this, RegisterFamiliarActivity::class.java)
            startActivity(intent)
        }
    }
}
