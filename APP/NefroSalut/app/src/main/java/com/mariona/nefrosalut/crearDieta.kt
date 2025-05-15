package com.mariona.nefrosalut

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.databinding.DietasBinding
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente
import com.mariona.nefrosalut.viewModels.CrearDietasViewModel
import com.mariona.nefrosalut.viewModels.CrearDietasViewModelFactory
import com.mariona.nefrosalut.viewModels.DietasViewModel
import com.mariona.nefrosalut.viewModels.DietasViewModelFactory

class crearDieta: AppCompatActivity() {

    private val viewModel: CrearDietasViewModel by viewModels { CrearDietasViewModelFactory() }
    private lateinit var binding: DietasBinding

    private lateinit var user: Any
    private lateinit var rol: String
    private lateinit var nomDieta: String

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_dieta)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel.crearDieta.observe(this) { dietas ->

            if(dietas.size>0){
                val snackbar = Snackbar.make(findViewById(R.id.root), "Dieta creada", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                snackbar.show()

                val intent = Intent(this, afegirPlats::class.java)
                if (rol.equals("Paciente")) {
                    intent.putExtra("user", user as Paciente)
                } else if (rol.equals("Familiar")) {
                    intent.putExtra("user", user as Familiar)
                }
                intent.putExtra("rol", rol)

                startActivity(intent)
            }

        }

        val btnGuardarDieta = findViewById<Button>(R.id.btnGuardarNomDieta)

        btnGuardarDieta.setOnClickListener {

            nomDieta = findViewById<EditText>(R.id.inputNomDieta).text.toString()
            if (nomDieta.isEmpty()) {
                val snackbar = Snackbar.make(binding.root, "El nom de la dieta no pot estar buit", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                snackbar.show()
                return@setOnClickListener
            }

            viewModel.crearDieta(nomDieta, rol, (user as Paciente).id)
        }

        rol = intent.extras!!.getString("rol").toString()
        if (rol.equals("Paciente")) {
            user = intent.extras!!.getSerializable("user") as Paciente
        }
        else if (rol.equals("Familiar")) {
            user = intent.extras!!.getSerializable("user") as Familiar
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