package com.mariona.nefrosalut.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mariona.nefrosalut.R
import com.mariona.nefrosalut.models.Aliments

class afegirPlatsAdapter(var aliments: List<Aliments> = emptyList(), var clasificacion: String, var context: Context, private val onCardClick: (Aliments) -> Unit)  : RecyclerView.Adapter<afegirPlatsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = itemView.findViewById(R.id.cardNomDelPlat)
        val categoria: TextView = itemView.findViewById(R.id.cardCategoriaPlat)
        val cardview: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_platos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alimento = aliments[position]
        holder.nombre.text = alimento.nombre
        holder.categoria.text = alimento.categoria

        if(alimento.clasificaciones[clasificacion] == 0) {
            holder.cardview.setBackgroundColor(ContextCompat.getColor(context, R.color.estadio0))
        } else if(alimento.clasificaciones[clasificacion] == 1){
            holder.cardview.setBackgroundColor(ContextCompat.getColor(context, R.color.estadio1))

        }else if(alimento.clasificaciones[clasificacion] == 2){
            holder.cardview.setBackgroundColor(ContextCompat.getColor(context, R.color.estadio2))
        }

        val cardview = holder.cardview

        cardview.setOnClickListener{
            onCardClick(alimento)

        }

    }

    override fun getItemCount(): Int {
        return aliments.size
    }
}