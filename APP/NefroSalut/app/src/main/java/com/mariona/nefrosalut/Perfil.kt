package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente

class Perfil : AppCompatActivity() {

    private lateinit var user: Any
    private lateinit var rol: String
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

        val btnDadesMediques = findViewById<Button>(R.id.btnDadesMediques)
        btnDadesMediques.setOnClickListener {
            paciente?.let {
                val intent = Intent(this, DadesMediquesMostrarActivity::class.java)
                // Pasamos los datos necesarios via intent
                intent.putExtra("dni", it.DNI)
                intent.putExtra("dataNaixement", it.fechaNacimiento)
                intent.putExtra("pes", it.peso)
                intent.putExtra("alcada", it.altura)
                intent.putExtra("activitatFisica", it.actividadFisica)
                intent.putExtra("hipertensio", it.hipertenso)
                intent.putExtra("diabetic", it.diabetico)
                intent.putExtra("estat", it.estadoEnfermedad)
                intent.putExtra("fase", it.estadio)
                intent.putExtra("nom", it.nombre)
                intent.putExtra("cognoms", it.apellidos)

                startActivity(intent)
            }

        }

        rol = intent.extras!!.getString("rol").toString()
        if (rol.equals("Paciente")) {
            user = intent.extras!!.getSerializable("user") as Paciente
        }
        else if (rol.equals("Familiar")) {
            user = intent.extras!!.getSerializable("user") as Familiar
        }

        fun verDadesMedicas(paciente: Paciente) {
            val intent = Intent(this, DadesMediquesMostrarActivity::class.java)
            // Pasamos los datos necesarios via intent
            intent.putExtra("nom", paciente.nombre)
            intent.putExtra("cognoms", paciente.apellidos)
            intent.putExtra("dni", paciente.DNI)
            intent.putExtra("dataNaixement", paciente.fechaNacimiento)
            intent.putExtra("pes", paciente.peso)
            intent.putExtra("alcada", paciente.altura)
            intent.putExtra("activitatFisica", paciente.actividadFisica)
            intent.putExtra("hipertensio", paciente.hipertenso)
            intent.putExtra("diabetic", paciente.diabetico)
            intent.putExtra("estat", paciente.estadoEnfermedad)
            intent.putExtra("fase", paciente.estadio)
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


    fun verProgresoClick(view: android.view.View) {
        val i = android.content.Intent(this, verProgreso::class.java)
        startActivity(i)
    }

    fun cerrarSesionClick(view: android.view.View) {
        val i = android.content.Intent(this, LoginSelectActivity::class.java)
        startActivity(i)
    }

//    fun onDadesMediquesClick(view: android.view.View) {
//        val paciente = intent.getSerializableExtra("user") as? Paciente
//
//        if (rol.equals("Paciente")) {
//            intent.putExtra("user", user as Paciente)
//        } else if (rol.equals("Familiar")) {
//            intent.putExtra("user", user as Familiar)
//        }
//        intent.putExtra("rol", rol)
//        paciente?.let {
//            verDadesMedicas(it)
//        }
//    }
}
