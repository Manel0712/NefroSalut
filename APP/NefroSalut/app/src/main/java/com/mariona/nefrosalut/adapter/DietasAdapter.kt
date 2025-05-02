package com.mariona.nefrosalut.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariona.nefrosalut.models.Dietas
import com.mariona.nefrosalut.R
import com.mariona.nefrosalut.databinding.CardDietasBinding
import com.mariona.nefrosalut.databinding.DietasBinding

class DietasAdapter(var dietas: List<Dietas> = emptyList()) : RecyclerView.Adapter<DietasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(view.inflate(R.layout.card_dietas, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dieta = dietas[position]
        holder.bind(dieta)
    }

    override fun getItemCount(): Int = dietas.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CardDietasBinding.bind(view)

        fun bind(dieta: Dietas) {
            binding.cardNomDieta.text = dieta.nombre
        }
    }
}