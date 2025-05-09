package com.mariona.nefrosalut

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class crearDieta: AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_dieta)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnAfegirPlats = findViewById<Button>(R.id.btnAfegirPlats)

        btnAfegirPlats.setOnClickListener {
            val intent = Intent(this, verPlatos::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnvideos -> startActivity(android.content.Intent(this, verVideos::class.java))
            R.id.btnjocs -> startActivity(android.content.Intent(this, Quiz::class.java))
            R.id.btnplats -> startActivity(android.content.Intent(this, dietasPlats::class.java))
            R.id.btnperfil -> startActivity(android.content.Intent(this, Perfil::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    //poner el nombre de la nueva dieta
    //val nombreDieta = findViewById<>(R.id.nomDieta)

    //cuando pone el nombre de la dieta se guarda se comprueba si lo ha llenado y añade platos

    /*fun afegirPlats(){
        val intent = Intent(this, verPlatos::class.java)
        startActivity(intent)
    }*/




}