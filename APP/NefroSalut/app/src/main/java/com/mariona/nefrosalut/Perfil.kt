package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mariona.nefrosalut.models.Paciente

class Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val paciente = intent.getSerializableExtra("user") as? Paciente

        val nombreTextView = findViewById<TextView>(R.id.inputUser)
        val emailTextView = findViewById<TextView>(R.id.inputEmail)

        nombreTextView.text = paciente?.nombre ?: "Nombre no disponible"
        emailTextView.text = paciente?.email ?: "Email no disponible"


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



    fun verProgresoClick(view: android.view.View) {
        val i = android.content.Intent(this, verProgreso::class.java)
        startActivity(i)
    }

    fun cerrarSesionClick(view: android.view.View) {
        //val i = android.content.Intent(this, verProgreso::class.java)
        //startActivity(i)
    }
}