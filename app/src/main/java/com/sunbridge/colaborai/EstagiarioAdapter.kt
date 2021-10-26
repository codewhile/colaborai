package com.sunbridge.colaborai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class EstagiarioAdapter(
    private val estagiarios: List<Estagiario>,
    private val context: Context,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<EstagiarioAdapter.ViewHolder>(),
        UpdateOrSaveFragment.EstagiarioSavedOrUpdateListener {

    private val imagesPessoas by lazy {
        context.resources.obtainTypedArray(R.array.pessoas)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(context).inflate(R.layout.aluno_adapter, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val (id, nome, periodo, faculdade, curso) =
            estagiarios[position]
        holder.image.setImageDrawable(imagesPessoas.getDrawable(id.toInt()))
        holder.name.text = nome
        holder.periodo.text = "Periodo $periodo"
        holder.faculdade.text = faculdade
        holder.curso.text = curso

        holder.itemView.setOnClickListener {

            val dialog = UpdateOrSaveFragment.newInstance(id)

            dialog.listener = this
                dialog.show(fragmentManager, UpdateOrSaveFragment.TAG)

        }


    }

    override fun getItemCount(): Int  =
        estagiarios.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val image:ImageView = itemView.findViewById(R.id.imageView)
        val name: TextView = itemView.findViewById<TextView>(R.id.txtName)
        val periodo: TextView = itemView.findViewById<TextView>(R.id.txtPeriodo)
        val faculdade: TextView = itemView.findViewById<TextView>(R.id.txtFaculdade)
        val curso: TextView = itemView.findViewById<TextView>(R.id.txtCurso)


    }

    override fun onEstagiarioSavedOrUpdated() {
        notifyDataSetChanged()
    }

}
