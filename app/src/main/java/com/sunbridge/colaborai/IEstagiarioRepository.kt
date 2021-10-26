package com.sunbridge.colaborai

interface IEstagiarioRepository {

    fun loadEstagiario(callback: (MutableList<Estagiario>) -> Unit)
    fun removeEstagiario(id: Long): Boolean
    fun addOrUpdateEstagiario(estagiario: Estagiario) : Boolean


}
