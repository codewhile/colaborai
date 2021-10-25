package com.sunbridge.colaborai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText

class UpdateFragment() :
    DialogFragment() {

    private val presenter =
        UpdatePresenter(AlunoRepository)

    var listener:AlunoSavedOrUpdateListener? = null



    val id: Long by lazy {
        arguments?.getLong(ID, 0) ?: 0
    }

    @JvmName("AddOrUpdateListener")
    fun setListener(savedOrUpdateListener:
                    AlunoSavedOrUpdateListener) {

        this.listener =
            savedOrUpdateListener

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_configure_aluno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        view?.let { view ->

            val button =
                view.findViewById<Button>(R.id.btnSave)

            button.setOnClickListener {

                val nome = view.findViewById<TextInputEditText>(R.id.inputName).text.toString()

                val nota = view.findViewById<TextInputEditText>(R.id.inputNota).text.toString().toFloat()

                val periodo = view.findViewById<TextInputEditText>(R.id.inputPeriodo).text.toString().toInt()

                val aluno = Aluno(id, nome, periodo, nota)

                if (presenter.save(aluno)) {

                    listener?.let {
                        listener!!.onAlunoSavedOrUpdated()
                    }

                    dialog?.dismiss()
                }
            }

        }

    }

    interface AlunoSavedOrUpdateListener {
        fun onAlunoSavedOrUpdated()
    }

    companion object {

        const val TAG =
            "update_tag_fragment"

        private const val ID =
            "fragment_aluno_id"

        fun newInstance(id: Long = 0) =

            UpdateFragment().apply {
                this.arguments = Bundle().apply {
                    putLong(ID, id)
                }
            }

    }

}