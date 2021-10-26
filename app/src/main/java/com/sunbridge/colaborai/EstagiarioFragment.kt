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

class EstagiarioFragment :
    Fragment(),
    IEstagiarioView {


    private val presenter =
        EstagiarioPresenter(this, EstagiarioRepository)

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
                    UpdateOrSaveFragment.newInstance(0)

                dialog.listener = object :
                    UpdateOrSaveFragment.EstagiarioSavedOrUpdateListener {

                    override fun onEstagiarioSavedOrUpdated() {
                        recycler.adapter?.notifyDataSetChanged()
                    }

                }

                dialog.show(parentFragmentManager, UpdateOrSaveFragment.TAG)

            }

        }
    }

    override fun showEstagiarios(estagiarios: MutableList<Estagiario>) {

        val recyclerStudents =
            view?.findViewById<RecyclerView>(R.id.recyclerView)

        val alunoAdapter =
            EstagiarioAdapter(estagiarios, requireContext(), parentFragmentManager)

        recyclerStudents?.adapter =
            alunoAdapter

        recyclerStudents?.layoutManager =
            GridLayoutManager(requireContext(), 3)


        initSwipeGesture(recyclerStudents, estagiarios)


    }

    private fun initSwipeGesture(recyclerStudents: RecyclerView?, estagiarios: MutableList<Estagiario>) {
        val swipe = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                presenter.deleteAlunById(estagiarios[position].id)
                recyclerStudents?.adapter?.notifyItemRemoved(position)

            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)

        itemTouchHelper.attachToRecyclerView(recyclerStudents)
    }

}

