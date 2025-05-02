package com.mariona.nefrosalut

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.widget.TextView
import android.content.Intent
import android.view.View
import androidx.activity.enableEdgeToEdge
import com.mariona.nefrosalut.databinding.RegistrePacientBinding
import com.google.android.material.snackbar.Snackbar

class RegisterPacientActivity : AppCompatActivity() {
    private lateinit var binding: RegistrePacientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = RegistrePacientBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun registraUsuari(view: View) {
        if (binding.etName.text.toString().equals("")||binding.etApellidos.text.toString().equals("") || binding.etEmail.text.toString().equals("") || binding.etTelefono.text.toString().equals("") || binding.etPassword.text.toString().equals("") || binding.etPassword2.text.toString().equals("")) {
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
            val i = Intent(this, DadesMediques::class.java)
            i.putExtra("name", binding.etName.text.toString())
            i.putExtra("apellidos", binding.etApellidos.text.toString())
            i.putExtra("email", binding.etEmail.text.toString())
            i.putExtra("telefono", binding.etTelefono.text.toString())
            i.putExtra("password", binding.etPassword.text.toString())
            startActivity(i)
        }
    }
}
