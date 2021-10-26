package com.sunbridge.colaborai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText

class UpdateOrSaveFragment() :
    DialogFragment() {

    private val presenter =
        UpdateOrSavePresenter(EstagiarioRepository)

    var listener:EstagiarioSavedOrUpdateListener? = null



    val id: Long by lazy {
        arguments?.getLong(ID, 0) ?: 0
    }

    @JvmName("AddOrUpdateListener")
    fun setListener(savedOrUpdateListener:
                    EstagiarioSavedOrUpdateListener) {

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

                val nome =
                    view.findViewById<TextInputEditText>(R.id.inputName).text.toString()

                val nota =
                    view.findViewById<TextInputEditText>(R.id.inputFaculdade).text.toString()

                val periodo =
                    view.findViewById<TextInputEditText>(R.id.inputPeriodo).text.toString().toInt()

                val faculdade =
                    view.findViewById<TextInputEditText>(R.id.inputFaculdade).text.toString()

                val curso =
                    view.findViewById<TextInputEditText>(R.id.inputCurso).text.toString()

                val aluno =
                    Estagiario(id, nome, periodo, faculdade, curso)

                if (presenter.save(aluno)) {

                    listener?.let {
                        listener!!.onEstagiarioSavedOrUpdated()
                    }

                    dialog?.dismiss()
                }
            }

        }

    }

    interface EstagiarioSavedOrUpdateListener {
        fun onEstagiarioSavedOrUpdated()
    }

    companion object {

        const val TAG =
            "EXTRA_UPDATE_TAG_FRAGMENT"

        private const val ID =
            "EXTRA_ESTAGIARIO_ID"

        fun newInstance(id: Long = 0) =

            UpdateOrSaveFragment().apply {
                this.arguments = Bundle().apply {
                    putLong(ID, id)
                }
            }

    }

}