package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.Aliments
import com.mariona.nefrosalut.models.Dietas
import kotlinx.coroutines.launch

class CrearDietasViewModel: ViewModel() {

    private val _crearDietasListLoading = MutableLiveData(false)
    public val crearDietasListLoading: LiveData<Boolean> get() = _crearDietasListLoading

    private val _crearDieta = MutableLiveData<List<Dietas>>(emptyList())
    public val crearDieta: LiveData<List<Dietas>> get() = _crearDieta

    private val _ponerPlato = MutableLiveData<List<Aliments>>(emptyList())
    public val ponerPlato: LiveData<List<Aliments>> get() = _ponerPlato

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    fun crearDieta(nombre: String, rol: String, usuario: Long) {
        viewModelScope.launch {
            _error.value = null
            var dieta = Dietas(0, nombre, rol, usuario)
            var resposta = Connection.nefroSalutService.crearDieta(dieta)

            if (resposta.isSuccessful) {
                _crearDieta.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }

    fun ponerPlatos(idDietas: Long, idPlatos: Long) {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.afegirPlatsDieta(idDietas, idPlatos)

            if (resposta.isSuccessful) {
                _ponerPlato.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}
@Suppress("UNCHECKED_CAST")
class CrearDietasViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return CrearDietasViewModel() as T
    }
}