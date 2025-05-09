package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
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
        val btnAfegirPlats = findViewById<Button>(R.id.btnAfegirPlats)
        val btnVerPlato = findViewById<Button>(R.id.btnVerPlato)
        val btnVerDieta = findViewById<Button>(R.id.btnVerDieta)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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