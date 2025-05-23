package com.mariona.nefrosalut

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mariona.nefrosalut.models.Progreso

class verProgreso: AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progres)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val progreso = intent.getSerializableExtra("progreso") as? Progreso
        val nombre = intent.getStringExtra("nombre")

        // Referencias a los TextView
        val nomPacientTextView = findViewById<TextView>(R.id.nom_pacient)
        val videosTextView = findViewById<TextView>(R.id.videos_vistos)
        val quizzesTextView = findViewById<TextView>(R.id.quizzes_fets)
        val dataIniciTextView = findViewById<TextView>(R.id.data_inici)
        val puntsTextView = findViewById<TextView>(R.id.num_punts)
        val monedesTextView = findViewById<TextView>(R.id.monedes)
        val power1TextView = findViewById<TextView>(R.id.power1)
        val power2TextView = findViewById<TextView>(R.id.power2)

        // Mostrar los valores
        nomPacientTextView.text = nombre ?: ""
        videosTextView.text = progreso?.powerUps?.get("videos_vistos")?.toString() ?: "0"
        quizzesTextView.text = progreso?.powerUps?.get("quizzes_fets")?.toString() ?: "0"
        dataIniciTextView.text = "01/01/2024" // Puedes hacerlo dinÃ¡mico si quieres
        puntsTextView.text = progreso?.puntos?.toString() ?: "0"
        monedesTextView.text = progreso?.monedas?.toString() ?: "0"
        power1TextView.text = "X${progreso?.powerUps?.get("power1") ?: 0}"
        power2TextView.text = "X${progreso?.powerUps?.get("power2") ?: 0}"
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
}