package com.example.NoteApp.adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.NoteApp.R
import com.example.NoteApp.RegisterNoteActivity
import com.example.NoteApp.databinding.CardviewNotaBinding
import com.example.NoteApp.entity.Nota



class NotasAdapter(private val notas: List<Nota>)
    : RecyclerView.Adapter<NotasAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_nota,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = notas[position].title.toString()
        holder.date.text = notas[position].fecha
        holder.containerNote.setCardBackgroundColor(Color.parseColor(notas[position].color))

        holder.containerNote.setOnClickListener{

            val intent = Intent(holder.itemView.context, RegisterNoteActivity::class.java)
            intent.putExtra("id",  notas[position].uid.toString())
            intent.putExtra("descripcion",  notas[position].descripcion.toString())
            intent.putExtra("titulo",  notas[position].title.toString())
            intent.putExtra("color",  notas[position].color)
            intent.putExtra("idColor",  notas[position].idColor.toString())
            startActivity(holder.itemView.context,intent,null)
        }




    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var binding = CardviewNotaBinding.bind(itemView)
        val title = binding.tvTitulo
        val date = binding.tvDate
        val containerNote = binding.layautNote


    }


}