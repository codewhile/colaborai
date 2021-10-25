package com.sunbridge.colaborai

interface IAlunoRepository {

    fun loadAlunos(callback: (MutableList<Aluno>) -> Unit)
    fun removeAluno(id: Long): Boolean
    fun addOrUpdateAluno(aluno: Aluno) : Boolean


}
