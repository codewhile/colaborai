package com.sunbridge.colaborai

class EstagiarioPresenter(private val view:IEstagiarioView,
                          private val repository: IEstagiarioRepository) {

    fun showAllAlunos() {

        repository.loadEstagiario { alunos ->
            view.showEstagiarios(alunos)
        }

    }

    fun deleteAlunById(id: Long) {
        repository.removeEstagiario(id)
        showAllAlunos()
    }

}