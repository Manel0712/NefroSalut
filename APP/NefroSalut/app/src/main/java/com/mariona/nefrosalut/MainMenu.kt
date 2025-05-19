package com.mariona.nefrosalut

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.mariona.nefrosalut.databinding.MainmenuBinding
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente

class MainMenu : AppCompatActivity() {
    private lateinit var user: Any
    private lateinit var rol: String
    private lateinit var binding: MainmenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = MainmenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        rol = intent.extras!!.getString("rol").toString()
        if (rol.equals("Paciente")) {
            user = intent!!.getParcelableExtra<Paciente>("user")!!
            var paciente: Paciente? = user as? Paciente
            paciente!!.nombre
        }
        else if (rol.equals("Familiar")) {
            user = intent.extras!!.getSerializable("user") as Familiar
        }
    }

    fun dietasClick(view: View) {
        val i = Intent(this, dietasPlats::class.java)
        startActivity(i)
    }

    fun videosClick(view: View) {
        val i = Intent(this, verVideos::class.java)
        startActivity(i)
    }

    fun quizClick(view: View) {
        val i = Intent(this, escogerQuiz::class.java)
        startActivity(i)
    }

    fun perfilClick(view: View) {
        val i = Intent(this, Perfil::class.java)
        startActivity(i)
    }




}