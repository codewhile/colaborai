package com.sunbridge.colaborai

object EstagiarioRepository :
    IEstagiarioRepository {

    private var cont:Long = 1

    // Lista em memória
    private val alunos = mutableListOf<Estagiario>(
        Estagiario(cont++, "Giulia dos Santos", 3, "UFRJ", "ADS"),
        Estagiario(cont++, "Ana Patrícia Tenório", 5, "UERJ", "ADS"),
        Estagiario(cont++, "Rafa batista da Silva", 4, "FAETRJ", "ADS"),
        Estagiario(cont++, "Luiciana De Oliveira", 2, "UNIRIO", "Ciência da Computação"),
        Estagiario(cont++, "Guilherme Fernando Junior", 10, "UNICARIOCA", "Ciência da Computação"),
        Estagiario(cont++, "Caio da Silva", 2, "UNIASALVE", "Ciência da Computação"),
        Estagiario(cont++, "Giulia dos Santos", 3, "UFF", "Redes"),
        Estagiario(cont++, "Ana Patrícia Tenório", 5, "PUC", "Redes"),
        Estagiario(cont++, "Rafa batista da Silva", 5, "FAETERJ", "Redes"),
        Estagiario(cont++, "Luiciana De Oliveira", 8, "UFRJ", "IA"),
        Estagiario(cont++, "Guilherme Fernando Junior", 3, "UMJP", "IA"),
        Estagiario(cont++, "Caio da Silva", 1, "FFERJ", "IA"),

        )


    override fun loadEstagiario(callback: (MutableList<Estagiario>) -> Unit) {
        callback(alunos)
    }

    override fun removeEstagiario(id: Long):
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

    override fun addOrUpdateEstagiario(estagiario: Estagiario)
            : Boolean {

        if (estagiario.id == 0L)
            alunos.add(estagiario.apply { id = cont++ })
        else {

            val finded = alunos.find { pessoa ->
                pessoa.id == estagiario.id
            }

            if (finded != null) {
                val index = alunos.indexOf(finded)
                alunos[index] = estagiario
            } else
                return false

        }


        return true
    }

}
