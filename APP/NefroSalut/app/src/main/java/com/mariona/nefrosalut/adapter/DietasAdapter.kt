package com.mariona.nefrosalut.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariona.nefrosalut.R
import com.mariona.nefrosalut.models.Aliments

class dietasAdapter(private val aliments: List<Aliments>) : RecyclerView.Adapter<dietasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = itemView.findViewById(R.id.cardNomDelPlat)
        val categoria: TextView = itemView.findViewById(R.id.cardCategoriaPlat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_dietas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alimento = aliments[position]
        holder.nombre.text = alimento.alimento
        holder.categoria.text = alimento.categoria
    }

    override fun getItemCount(): Int {
        return aliments.size
    }
}