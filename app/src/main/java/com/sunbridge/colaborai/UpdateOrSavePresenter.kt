package com.sunbridge.colaborai

class UpdateOrSavePresenter(
    private val repository: IEstagiarioRepository) {

    fun save(estagiario: Estagiario): Boolean {
        return repository.addOrUpdateEstagiario(estagiario)
    }

}