package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.Dietas
import kotlinx.coroutines.launch

class DietasViewModel: ViewModel() {
    private val _dietasListLoading = MutableLiveData(false)
    public val dietasListLoading: LiveData<Boolean> get() = _dietasListLoading

    private val _dieta = MutableLiveData<List<Dietas>>(emptyList())
    public val dieta: LiveData<List<Dietas>> get() = _dieta

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    init {
        loadDietas()
    }

    public fun loadDietas() {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.dietas()
            if (resposta.isSuccessful) {
                _dieta.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DietasViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return DietasViewModel() as T
    }
}