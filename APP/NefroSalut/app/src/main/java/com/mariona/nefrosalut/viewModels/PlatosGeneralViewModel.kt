package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.Aliments
import com.mariona.nefrosalut.models.Dietas
import com.mariona.nefrosalut.models.Platos
import kotlinx.coroutines.launch

class PlatosGeneralViewModel: ViewModel() {
    private val _platosListLoading = MutableLiveData(false)
    public val platosListLoading: LiveData<Boolean> get() = _platosListLoading

    private val _plato = MutableLiveData<List<Aliments>>(emptyList())
    public val plato: LiveData<List<Aliments>> get() = _plato

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun loadPlatos() {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.platos()
            if (resposta.isSuccessful) {
                _plato.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }

    public fun loadPlatosCategoria(categoria: String) {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.platosCategoria(categoria)
            if (resposta.isSuccessful) {
                _plato.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class PlatosGeneralViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return PlatosGeneralViewModel() as T
    }
}