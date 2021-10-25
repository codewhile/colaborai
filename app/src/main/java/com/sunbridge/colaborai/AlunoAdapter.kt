package com.sunbridge.colaborai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class AlunoAdapter(
    private val alunos: List<Aluno>,
    private val context: Context,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<AlunoAdapter.ViewHolder>(), UpdateFragment.AlunoSavedOrUpdateListener {

    private val imagesPessoas by lazy {
        context.resources.obtainTypedArray(R.array.pessoas)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(context).inflate(R.layout.aluno_adapter, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val (id, nome, periodo, nota) =
            alunos[position]
        holder.image.setImageDrawable(imagesPessoas.getDrawable(id.toInt()))
        holder.name.text = nome
        holder.nota.text = "Nota $nota"
        holder.periodo.text = "Periodo $periodo"

        holder.itemView.setOnClickListener {

            val dialog = UpdateFragment.newInstance(id)

            dialog.listener = this
                dialog.show(fragmentManager, UpdateFragment.TAG)

        }


    }

    override fun getItemCount(): Int  =
        alunos.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val image:ImageView = itemView.findViewById(R.id.imageView)
        val nota: TextView = itemView.findViewById<TextView>(R.id.txtNota)
        val name: TextView = itemView.findViewById<TextView>(R.id.txtName)
        val periodo: TextView = itemView.findViewById<TextView>(R.id.txtPeriodo)

    }

    override fun onAlunoSavedOrUpdated() {
        notifyDataSetChanged()
    }

}
