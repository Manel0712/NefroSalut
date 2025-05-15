package com.mariona.nefrosalut

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.databinding.RegisterFamiliarBinding
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.viewModels.RegisterFamiliarViewModel
import com.mariona.nefrosalut.viewModels.RegisterFamiliarViewModelFactory

class RegisterFamiliarActivity : AppCompatActivity() {
    private val viewModel: RegisterFamiliarViewModel by viewModels { RegisterFamiliarViewModelFactory() }
    private lateinit var binding: RegisterFamiliarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = RegisterFamiliarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.error.observe(this) {
            if (it.equals("Familiar registrado correctamente")) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            else if (it != null) {
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
    fun registraFamiliar(view: View) {
        if (binding.etName.text.toString().equals("")|| binding.etApellidos.text.toString().equals("")|| binding.etEmail.text.toString().equals("") || binding.etTelefono.text.toString().equals("") || binding.etPassword.text.toString().equals("") || binding.etPassword2.text.toString().equals("")) {
            val snackbar = Snackbar.make(view, "No pueden haver campos vacios",
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

        else if (!binding.etPassword2.text.toString().equals(binding.etPassword.text.toString())) {
            val snackbar = Snackbar.make(view, "Las contraseñas no coinciden",
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

        else {
            var familiar = Familiar(0,binding.etName.text.toString(), binding.etApellidos.text.toString(), binding.etEmail.text.toString(), binding.etTelefono.text.toString(), binding.etPassword.text.toString().trim(), null)
            viewModel.register(familiar)
        }
    }
}
