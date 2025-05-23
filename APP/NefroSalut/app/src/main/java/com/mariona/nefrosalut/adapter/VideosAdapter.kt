package com.mariona.nefrosalut.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariona.nefrosalut.R
import com.mariona.nefrosalut.models.Videos

class VideosAdapter(var videos: List<Videos>) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreVideo: TextView = itemView.findViewById(R.id.cardVideo)
        val categoriaVideo: TextView = itemView.findViewById(R.id.cardCategoriaVideo)
        val linkVideo: ImageView = itemView.findViewById(R.id.cardLinkVideo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_videos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videos = videos[position]
        holder.nombreVideo.text = videos.titulo
        holder.categoriaVideo.text = videos.categoria

        holder.linkVideo.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videos.link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }


}