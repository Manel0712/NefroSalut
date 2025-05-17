package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mariona.nefrosalut.models.QuizModelo

class Quiz : AppCompatActivity() {

    private lateinit var categoria: String
    lateinit var pregunta: TextView
    lateinit var opcio1: Button
    lateinit var opcio2: Button
    lateinit var opcio3: Button
    lateinit var numPregunta: TextView
    lateinit private var puntos: TextView
    private var questionsList: MutableList<QuizModelo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        pregunta = findViewById(R.id.pregunta)
        opcio1 = findViewById(R.id.op1)
        opcio2 = findViewById(R.id.op2)
        opcio3 = findViewById(R.id.op3)
        numPregunta = findViewById(R.id.numPregunta)
        puntos = findViewById(R.id.numPuntos)

        if(categoria == "aleatorio") {
            preguntasCategoriaAleatoria()
        } else if(categoria == "predialisis") {
            preguntasCategoriaPredialisis()
        } else if(categoria == "hemodialisis") {
            preguntasCategoriaHemodialisis()
        } else if(categoria == "peritoneal") {
            preguntasCategoriaPeritoneal()
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

    fun preguntasCategoriaPredialisis() {

    }
    fun preguntasCategoriaHemodialisis() {

    }
    fun preguntasCategoriaPeritoneal() {

    }

    fun preguntasCategoriaAleatoria() {

    }

}