package com.mariona.nefrosalut

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mariona.nefrosalut.adapter.PlatosAdapter
import com.mariona.nefrosalut.adapter.afegirPlatsAdapter
import com.mariona.nefrosalut.databinding.PlatsBinding
import com.mariona.nefrosalut.models.Aliments
import com.mariona.nefrosalut.models.Dietas
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.Paciente
import com.mariona.nefrosalut.models.Platos
import com.mariona.nefrosalut.viewModels.CrearDietasViewModel
import com.mariona.nefrosalut.viewModels.CrearDietasViewModelFactory
import com.mariona.nefrosalut.viewModels.PlatosGeneralViewModel
import com.mariona.nefrosalut.viewModels.PlatosGeneralViewModelFactory

class afegirPlats : AppCompatActivity() {
    private val viewModel: PlatosGeneralViewModel by viewModels { PlatosGeneralViewModelFactory() }
    private val viewModel2: CrearDietasViewModel by viewModels { CrearDietasViewModelFactory() }
    private lateinit var binding: PlatsBinding

    private lateinit var user: Any
    private lateinit var rol: String

    lateinit private var afegirPlatsAdapter: afegirPlatsAdapter
    private lateinit var dieta: Dietas
    var inicializado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = PlatsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        rol = intent.extras!!.getString("rol").toString()
        if (rol.equals("Paciente")) {
            user = intent.extras!!.getSerializable("user") as Paciente
            var paciente: Paciente? = user as? Paciente
            afegirPlatsAdapter = afegirPlatsAdapter(emptyList(), paciente!!.clasificacion, this, {ponerPlatos(it)})
        }
        else if (rol.equals("Familiar")) {
            user = intent.extras!!.getSerializable("user") as Familiar
        }

        dieta = intent.extras!!.getSerializable("dieta") as Dietas

        binding.rvVerDietas.adapter = afegirPlatsAdapter

        viewModel.platosListLoading.observe(this) { cargando ->
            if (cargando) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.plato.observe(this) { platos ->
            if (platos.size != 0) {
                afegirPlatsAdapter.aliments = platos
                afegirPlatsAdapter.notifyDataSetChanged()
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

        viewModel2.ponerPlato.observe(this) { platos ->
            if(platos.size>0){
                val snackbar = Snackbar.make(findViewById(R.id.root), "Plat afegit correctament", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                snackbar.show()
            }

        }

        viewModel.loadPlatos()

        val spinner: Spinner = binding.spinerCategoriaPlat

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (inicializado) {
                    val seleccionado = parent.getItemAtPosition(position).toString()
                    if(seleccionado.equals("Tots")){
                        viewModel.loadPlatos()
                    }else{
                        viewModel.loadPlatosCategoria(seleccionado)
                    }
                }
                else {
                    inicializado = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
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

    fun ponerPlatos(plato: Aliments){
        viewModel2.ponerPlatos(dieta.id, plato.id)

    }
}