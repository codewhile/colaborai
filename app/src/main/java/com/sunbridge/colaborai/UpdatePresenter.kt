package com.sunbridge.colaborai

class UpdatePresenter(
    private val repository: IAlunoRepository) {

    fun save(aluno: Aluno): Boolean {
        return repository.addOrUpdateAluno(aluno)
    }

}