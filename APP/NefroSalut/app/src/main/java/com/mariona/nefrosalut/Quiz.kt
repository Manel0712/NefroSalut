package com.mariona.nefrosalut

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.mariona.nefrosalut.models.QuizModelo
import com.mariona.nefrosalut.viewModels.QuizViewModel
import com.mariona.nefrosalut.viewModels.QuizViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Quiz : AppCompatActivity() {

    private lateinit var categoria: String
    lateinit var layoutQuiz: ConstraintLayout
    lateinit var layoutResultado: ConstraintLayout
    lateinit var pregunta: TextView
    lateinit var opcio1: Button
    lateinit var opcio2: Button
    lateinit var opcio3: Button
    lateinit var numPregunta: TextView
    lateinit private var puntos: TextView
    lateinit var btnFinalitzar: Button
    lateinit var monedas: TextView
    lateinit var porDos: TextView
    lateinit var disolver: TextView
    private var questionsList: MutableList<QuizModelo> = ArrayList()
    private val viewModel: QuizViewModel by viewModels { QuizViewModelFactory() }
    var preguntas: List<QuizModelo>? = null

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
        puntos.text = "0"
        monedas = findViewById(R.id.numMoedasResultado)
        monedas.text = "0"
        disolver = findViewById(R.id.numDisolverResultado)
        disolver.text  = "0"
        porDos = findViewById(R.id.porDosResultado)
        porDos.text = "0"

        btnFinalitzar = findViewById(R.id.btnVolverMenu)
        btnFinalitzar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }



        categoria = intent.extras!!.getString("categoria").toString()

        if(categoria == "aleatorio") {
            preguntasCategoriaAleatoria()
        } else if(categoria == "Prediàlisi") {
            preguntasCategoriaPredialisis()
        } else if(categoria == "Hemodiálisis") {
            preguntasCategoriaHemodialisis()
        } else if(categoria == "Peritoneal") {
            preguntasCategoriaPeritoneal()
        }

        viewModel.quiz.observe(this) { quizzes ->
            preguntas = viewModel.quiz.value
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
        if (opcio1.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
            opcio1.setBackgroundResource(R.drawable.rounded_button_correcto)
            puntos = findViewById(R.id.numPuntos)
            puntos.text = (puntos.text.toString().toInt() + 10).toString()
            monedas.text = (monedas.text.toString().toInt() + 10).toString()
        } else {
            opcio1.setBackgroundResource(R.drawable.rounded_button_incorrecto)
            if (opcio2.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
                opcio2.setBackgroundResource(R.drawable.rounded_button_correcto)
            }
            else if (opcio3.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
                opcio3.setBackgroundResource(R.drawable.rounded_button_correcto)
            }
        }
        nuevaPregunta()
    }

    fun onClickOpcion2(view: View) {
        if (opcio2.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
            // Correct answer - set background to green
            opcio2.setBackgroundResource(R.drawable.rounded_button_correcto)
            puntos = findViewById(R.id.numPuntos)
            puntos.text = (puntos.text.toString().toInt() + 10).toString()
            monedas.text = (monedas.text.toString().toInt() + 10).toString()
        } else {
            // Incorrect answer
            opcio2.setBackgroundResource(R.drawable.rounded_button_incorrecto)
            if (opcio1.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
                opcio1.setBackgroundResource(R.drawable.rounded_button_correcto)
            }
            else if (opcio3.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
                opcio3.setBackgroundResource(R.drawable.rounded_button_correcto)
            }
        }
        nuevaPregunta()
    }

    fun onClickOpcion3(view: View) {
        if (opcio3.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
            opcio3.setBackgroundResource(R.drawable.rounded_button_correcto)
            puntos = findViewById(R.id.numPuntos)
            puntos.text = (puntos.text.toString().toInt() + 10).toString()
            monedas.text = (monedas.text.toString().toInt() + 10).toString()
        } else {
            opcio3.setBackgroundResource(R.drawable.rounded_button_incorrecto)
            if (opcio1.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
                opcio1.setBackgroundResource(R.drawable.rounded_button_correcto)
            }
            else if (opcio2.text == viewModel.quiz.value?.get(numPregunta.text.toString().toInt()-1)?.correctOption) {
                opcio2.setBackgroundResource(R.drawable.rounded_button_correcto)
            }
        }
        nuevaPregunta()
    }

    fun nuevaPregunta() {

        lifecycleScope.launch {
            delay(2_000)

            if(numPregunta.text.toString().toInt() == 5){
                layoutResultado.visibility = View.VISIBLE
                layoutQuiz.visibility = View.GONE

                if(puntos.text.toString().toInt() == 50){
                    porDos.text = (porDos.text.toString().toInt() + 1).toString()
                    disolver.text = (porDos.text.toString().toInt() + 1).toString()
                }

                monedas.findViewById<TextView>(R.id.numMoedasResultado)
                puntos.findViewById<TextView>(R.id.numPuntosResultado)
                porDos.findViewById<TextView>(R.id.porDosResultado)
                disolver.findViewById<TextView>(R.id.numDisolverResultado)

            }
            else{
                opcio1.text = preguntas!![numPregunta.text.toString().toInt()].option1
                opcio2.text = preguntas!![numPregunta.text.toString().toInt()].option2
                opcio3.text = preguntas!![numPregunta.text.toString().toInt()].option3
                pregunta.text = preguntas!![numPregunta.text.toString().toInt()].pregunta


                opcio1.setBackgroundResource(R.drawable.rounded_button)
                opcio2.setBackgroundResource(R.drawable.rounded_button)
                opcio3.setBackgroundResource(R.drawable.rounded_button)

                numPregunta.text = (numPregunta.text.toString().toInt() + 1).toString()
            }
        }
    }


    fun preguntasCategoriaPredialisis() {
        viewModel.loadQuizCategoria("Prediàlisi")
    }
    fun preguntasCategoriaHemodialisis() {
        viewModel.loadQuizCategoria("Hemodiálisis")
    }
    fun preguntasCategoriaPeritoneal() {
        viewModel.loadQuizCategoria("Peritoneal")
    }

    fun preguntasCategoriaAleatoria() {
        viewModel.loadQuiz()
    }

}