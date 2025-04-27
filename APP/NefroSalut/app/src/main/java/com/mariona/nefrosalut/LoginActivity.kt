package com.mariona.nefrosalut

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.databinding.LoginBinding
import com.mariona.nefrosalut.models.Paciente
import com.mariona.nefrosalut.viewModels.LogginPacienteViewModel
import com.mariona.nefrosalut.viewModels.LogginPacienteViewModelFactory

class LoginActivity : AppCompatActivity() {
    private val viewModel: LogginPacienteViewModel by viewModels { LogginPacienteViewModelFactory() }
    private lateinit var binding: LoginBinding
    private lateinit var paciente: List<Paciente>
    private lateinit var email: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = LoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.paciente.observe(this) { pacientes ->
            paciente = pacientes
            if (paciente.size != 0) {
                val i = Intent(this, MainMenu::class.java)
                i.putExtra("user", paciente[0])
                i.putExtra("rol", "Paciente")
                startActivity(i)
            }
        }

        viewModel.error.observe(this) {
            if (it != null) {
                val snackbar = Snackbar.make(view, it,
                    Snackbar.LENGTH_LONG).setAction("Action", null)
                snackbar.setActionTextColor(Color.WHITE)
                val snackbarView = snackbar.view
                snackbarView.setBackgroundColor(Color.RED)
                val textView =
                    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                textView.textSize = 28f
                snackbar.show()
            }
        }
    }

    fun loggin(view: View) {
        email = binding.etLoginEmail.text.toString().trim()
        password = binding.etLoginPassword.text.toString().trim()
        viewModel.loggin(email, password)
    }
}
