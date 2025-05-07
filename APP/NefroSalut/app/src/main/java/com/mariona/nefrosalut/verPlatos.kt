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
import com.mariona.nefrosalut.databinding.PlatsBinding
import com.mariona.nefrosalut.viewModels.PlatosViewModel
import com.mariona.nefrosalut.viewModels.PlatosViewModelFactory

class verPlatos : AppCompatActivity() {
    private val viewModel: PlatosViewModel by viewModels { PlatosViewModelFactory() }
    private lateinit var binding: PlatsBinding
    private val platosAdapter = PlatosAdapter(emptyList())
    var dieta: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = PlatsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //dieta = intent.extras!!.getLong("dieta").toInt()

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

        /*val spinner: Spinner = binding.spinerCategoriaPlat

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccionado = parent.getItemAtPosition(position).toString()
                viewModel.loadPlatosCategoria(dieta, seleccionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }*/
    }
}