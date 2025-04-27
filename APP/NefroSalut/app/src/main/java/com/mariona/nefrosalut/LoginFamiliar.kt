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
import com.mariona.nefrosalut.databinding.LoginfamiliarBinding
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.viewModels.LogginFamiliarViewModel
import com.mariona.nefrosalut.viewModels.LogginFamiliarViewModelFactory

class LoginFamiliar : AppCompatActivity() {
    private val viewModel: LogginFamiliarViewModel by viewModels { LogginFamiliarViewModelFactory() }
    private lateinit var binding: LoginfamiliarBinding
    private lateinit var familiar: List<Familiar>
    private lateinit var email: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = LoginfamiliarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.familiar.observe(this) { familiars ->
            familiar = familiars
            if (familiar.size != 0) {
                val i = Intent(this, MainMenu::class.java)
                i.putExtra("user", familiar[0])
                i.putExtra("rol", "Familiar")
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