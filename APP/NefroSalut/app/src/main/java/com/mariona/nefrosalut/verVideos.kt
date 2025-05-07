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
import com.mariona.nefrosalut.adapter.VideosAdapter
import com.mariona.nefrosalut.databinding.ListVideosBinding
import com.mariona.nefrosalut.viewModels.VideosViewModelFactory
import com.mariona.nefrosalut.viewModels.VideosViewModel

class verVideos : AppCompatActivity() {
    private val viewModel: VideosViewModel by viewModels { VideosViewModelFactory() }
    private lateinit var binding: ListVideosBinding
    private val videosAdapter = VideosAdapter(emptyList())
    var activacion: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ListVideosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = videosAdapter

        viewModel.videosListLoading.observe(this) { cargando ->
            if (cargando) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.video.observe(this) { videos ->
            if (videos.size != 0) {
                videosAdapter.videos = videos
                videosAdapter.notifyDataSetChanged()
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

        viewModel.loadVideos()

        val spinner: Spinner = binding.spinner

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (activacion) {
                    val seleccionado = parent.getItemAtPosition(position).toString()
                    viewModel.loadVideosCategoria(seleccionado)
                }
                else {
                    activacion = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
}