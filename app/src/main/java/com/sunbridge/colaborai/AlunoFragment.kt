package com.sunbridge.colaborai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AlunoFragment :
    Fragment(),
    IAlunosView {


    private val presenter =
        AlunoPresenter(this, AlunoRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater
            .from(requireContext()).inflate(R.layout.fragment_aluno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.showAllAlunos()
        configureAddButton()
    }

    private fun configureAddButton() {
        view?.let{

            val recycler = it.findViewById<RecyclerView>(R.id.recyclerView)

            it.findViewById<FloatingActionButton>(R.id.btnAdd).setOnClickListener {

                val dialog =
                    UpdateFragment.newInstance(0)

                dialog.listener = object :
                    UpdateFragment.AlunoSavedOrUpdateListener {

                    override fun onAlunoSavedOrUpdated() {
                        recycler.adapter?.notifyDataSetChanged()
                    }

                }

                dialog.show(parentFragmentManager, UpdateFragment.TAG)

            }

        }
    }

    override fun showAlunos(alunos: MutableList<Aluno>) {

        val recyclerStudents =
            view?.findViewById<RecyclerView>(R.id.recyclerView)

        val alunoAdapter =
            AlunoAdapter(alunos, requireContext(), parentFragmentManager)

        recyclerStudents?.adapter =
            alunoAdapter

        recyclerStudents?.layoutManager =
            GridLayoutManager(requireContext(), 3)


        initSwipeGesture(recyclerStudents, alunos)


    }

    private fun initSwipeGesture(recyclerStudents: RecyclerView?, alunos: MutableList<Aluno>) {
        val swipe = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                presenter.deleteAlunById(alunos[position].id)
                recyclerStudents?.adapter?.notifyItemRemoved(position)

            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)

        itemTouchHelper.attachToRecyclerView(recyclerStudents)
    }

}

