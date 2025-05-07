package com.mariona.nefrosalut

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.adapter.PlatosAdapter
import com.mariona.nefrosalut.databinding.VerDietasBinding
import com.mariona.nefrosalut.viewModels.PlatosViewModel
import com.mariona.nefrosalut.viewModels.PlatosViewModelFactory

class Platos : AppCompatActivity() {
    private val viewModel: PlatosViewModel by viewModels { PlatosViewModelFactory() }
    private lateinit var binding: VerDietasBinding
    private val platosAdapter = PlatosAdapter(emptyList())
    var dieta: Int = 0
    private lateinit var nombreDieta: String
    var activacion: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = VerDietasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dieta = intent.extras!!.getLong("dieta").toInt()
        nombreDieta = intent.extras!!.getString("nombreDieta").toString()

        binding.nomDieta.text = nombreDieta

        binding.rvVerDietas.adapter = platosAdapter

        viewModel.platosListLoading.observe(this) { cargando ->
            if (cargando) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.plato.observe(this) { platos ->
            if (platos.size != 0) {
                platosAdapter.aliments = platos
                platosAdapter.notifyDataSetChanged()
            }
        }

        viewModel.error.observe(this) {
            if (it != null) {
                val snackbar = Snackbar.make(
                    view, it,
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null)
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

        viewModel.loadPlatos(dieta)
    }
}