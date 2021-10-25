package com.sunbridge.colaborai

object AlunoRepository :
    IAlunoRepository {

    private var cont:Long = 1

    // Lista em memória
    private val alunos = mutableListOf<Aluno>(
        Aluno(cont++, "Giulia dos Santos", 5, 7f),
        Aluno(cont++, "Ana Patrícia Tenório", 5, 10f),
        Aluno(cont++, "Rafa batista da Silva", 4, 8f),
        Aluno(cont++, "Luiciana De Oliveira", 5, 6f),
        Aluno(cont++, "Guilherme Fernando Junior", 1, 3f),
        Aluno(cont++, "Caio da Silva", 2, 5f),
        Aluno(cont++, "Giulia dos Santos", 5, 7f),
        Aluno(cont++, "Ana Patrícia Tenório", 5, 10f),
        Aluno(cont++, "Rafa batista da Silva", 4, 8f),
        Aluno(cont++, "Luiciana De Oliveira", 5, 6f),
        Aluno(cont++, "Guilherme Fernando Junior", 1, 3f),
        Aluno(cont++, "Caio da Silva", 2, 5f),

        )


    override fun loadAlunos(callback: (MutableList<Aluno>) -> Unit) {
        callback(alunos)
    }

    override fun removeAluno(id: Long):
            Boolean {

        alunos.find { aluno ->
            aluno.id == id
        }.also {

            if (it != null) {
                alunos.remove(it)
            } else {
                return false
            }
        }

        return true

    }

    override fun addOrUpdateAluno(aluno: Aluno)
            : Boolean {

        if (aluno.id == 0L)
            alunos.add(aluno.apply { id = cont++ })
        else {

            val finded = alunos.find { pessoa ->
                pessoa.id == aluno.id
            }

            if (finded != null) {
                val index = alunos.indexOf(finded)
                alunos[index] = aluno
            } else
                return false

        }


        return true
    }

}
