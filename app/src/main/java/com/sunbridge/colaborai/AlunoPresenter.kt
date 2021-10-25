package com.sunbridge.colaborai

class AlunoPresenter(private val view:IAlunosView,
                     private val repository: IAlunoRepository) {

    fun showAllAlunos() {

        repository.loadAlunos { alunos ->
            view.showAlunos(alunos)
        }

    }

    fun deleteAlunById(id: Long) {
        repository.removeAluno(id)
        showAllAlunos()
    }

}