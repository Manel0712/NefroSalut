package com.mariona.nefrosalut

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.adapter.DietasAdapter
import com.mariona.nefrosalut.viewModels.DietasViewModel
import com.mariona.nefrosalut.viewModels.DietasViewModelFactory
import com.mariona.nefrosalut.databinding.DietasBinding

class Dietas : AppCompatActivity() {
    private val viewModel: DietasViewModel by viewModels { DietasViewModelFactory() }
    private lateinit var binding: DietasBinding
    private val dietasAdapter = DietasAdapter(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DietasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvVerDietas.adapter = dietasAdapter

        viewModel.dietasListLoading.observe(this) { cargando ->
            if (cargando) {
                binding.progress.visibility = View.VISIBLE
            }
            else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.dieta.observe(this) { dietas ->
            dietasAdapter.dietas = dietas
            dietasAdapter.notifyDataSetChanged()
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
}