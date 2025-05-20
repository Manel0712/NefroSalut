package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mariona.nefrosalut.models.QuizModelo
import com.mariona.nefrosalut.viewModels.QuizViewModel
import com.mariona.nefrosalut.viewModels.QuizViewModelFactory

class Quiz : AppCompatActivity() {

    private lateinit var categoria: String
    lateinit var pregunta: TextView
    lateinit var opcio1: Button
    lateinit var opcio2: Button
    lateinit var opcio3: Button
    lateinit var numPregunta: TextView
    lateinit private var puntos: TextView
    private var questionsList: MutableList<QuizModelo> = ArrayList()
    private val viewModel: QuizViewModel by viewModels { QuizViewModelFactory() }

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

        categoria = intent.extras!!.getString("categoria").toString()

        if(categoria == "aleatorio") {
            preguntasCategoriaAleatoria()
        } else if(categoria == "predialisis") {
            preguntasCategoriaPredialisis()
        } else if(categoria == "hemodialisis") {
            preguntasCategoriaHemodialisis()
        } else if(categoria == "peritoneal") {
            preguntasCategoriaPeritoneal()
        }

        viewModel.quiz.observe(this) { quizzes ->
            if (quizzes.size != 0) {
                numPregunta.text = "1"
                opcio1.text = quizzes[0].option1
                opcio2.text = quizzes[0].option2
                opcio3.text = quizzes[0].option3
                pregunta.text = quizzes[0].pregunta
            }
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

    fun onClickOpcion1(view: View) {
        if (opcio1.text == viewModel.quiz.value?.get(0)?.correctOption) {
            opcio1.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        } else {
            opcio1.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
        }
    }

    fun onClickOpcion2(view: View) {
        if (opcio2.text == viewModel.quiz.value?.get(0)?.correctOption) {
            // Correct answer - set background to green
            opcio2.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        } else {
            // Incorrect answer
            opcio2.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
        }
    }

    fun onClickOpcion3(view: View) {
        if (opcio3.text == viewModel.quiz.value?.get(0)?.correctOption) {
            opcio3.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        } else {
            opcio3.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
        }
    }


    fun preguntasCategoriaPredialisis() {

    }
    fun preguntasCategoriaHemodialisis() {

    }
    fun preguntasCategoriaPeritoneal() {

    }

    fun preguntasCategoriaAleatoria() {
        viewModel.loadQuiz()
    }

}