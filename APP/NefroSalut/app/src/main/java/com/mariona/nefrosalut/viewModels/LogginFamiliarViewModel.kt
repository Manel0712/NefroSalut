package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.LoginPaciente
import com.mariona.nefrosalut.models.Paciente
import kotlinx.coroutines.launch

class LogginFamiliarViewModel: ViewModel() {
    private val _familiarListLoading = MutableLiveData(false)
    public val familiarListLoading: LiveData<Boolean> get() = _familiarListLoading

    private val _familiar = MutableLiveData<List<Familiar>>(emptyList())
    public val familiar: LiveData<List<Familiar>> get() = _familiar

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun loggin(email: String, password: String) {
        viewModelScope.launch {
            _error.value = null
            var loginPaciente = LoginPaciente(email, password)
            var resposta = Connection.nefroSalutService.logginFamiliar(loginPaciente.email, loginPaciente.password)
            if (resposta.isSuccessful) {
                _familiar.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LogginFamiliarViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return LogginFamiliarViewModel() as T
    }
}