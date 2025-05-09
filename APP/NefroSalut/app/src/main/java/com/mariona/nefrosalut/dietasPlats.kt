package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.InputEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class dietasPlats  : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dietas_plats)
        val btnCrearDieta = findViewById<Button>(R.id.btnCrearDieta)
        val btnVerPlato = findViewById<Button>(R.id.btnVerPlato)
        val btnVerDieta = findViewById<Button>(R.id.btnVerDieta)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnCrearDieta.setOnClickListener {
            val intent = Intent(this, crearDieta::class.java)
            startActivity(intent)
        }

        btnVerPlato.setOnClickListener {
            val intent = Intent(this, verPlatos::class.java)
            startActivity(intent)
        }

        btnVerDieta.setOnClickListener {
            val intent = Intent(this, Dietas::class.java)
            startActivity(intent)
        }

        //poner el nombre de la nueva dieta
        //val nombreDieta = findViewById<>(R.id.nomDieta)

        //cuando pone el nombre de la dieta se guarda



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnvideos -> startActivity(Intent(this, verVideos::class.java))
            R.id.btnjocs -> startActivity(Intent(this, Quiz::class.java))
            R.id.btnplats -> startActivity(Intent(this, dietasPlats::class.java))
            R.id.btnperfil -> startActivity(Intent(this, Perfil::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}