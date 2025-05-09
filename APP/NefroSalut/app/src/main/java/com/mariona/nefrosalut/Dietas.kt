package com.mariona.nefrosalut

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.adapter.DietasAdapter
import com.mariona.nefrosalut.viewModels.DietasViewModel
import com.mariona.nefrosalut.viewModels.DietasViewModelFactory
import com.mariona.nefrosalut.databinding.DietasBinding
import com.mariona.nefrosalut.models.Dietas

class Dietas : AppCompatActivity() {
    private val viewModel: DietasViewModel by viewModels { DietasViewModelFactory() }
    private lateinit var binding: DietasBinding
    private val dietasAdapter = DietasAdapter(emptyList(), {platos(it)})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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

    fun platos(dieta: Dietas) {
        val i = Intent(this, verPlatos::class.java)

        i.putExtra("dieta", dieta.id)
        i.putExtra("nombreDieta", dieta.nombre)
        startActivity(i)
    }

}