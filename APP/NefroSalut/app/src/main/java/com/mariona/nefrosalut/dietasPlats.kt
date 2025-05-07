package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class dietasPlats  : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dietas_plats)
        val btnAfegirPlats = findViewById<Button>(R.id.btnAfegirPlats)
        val btnVerPlato = findViewById<Button>(R.id.btnVerPlato)
        val btnVerDieta = findViewById<Button>(R.id.btnVerDieta)

        /*btnAfegirPlats.setOnClickListener {
            val intent = Intent(this, AfegirPlats::class.java)
            startActivity(intent)
        }*/

        btnVerPlato.setOnClickListener {
            val intent = Intent(this, verPlatos::class.java)
            startActivity(intent)
        }

        btnVerDieta.setOnClickListener {
            val intent = Intent(this, Dietas::class.java)
            startActivity(intent)
        }


    }

}