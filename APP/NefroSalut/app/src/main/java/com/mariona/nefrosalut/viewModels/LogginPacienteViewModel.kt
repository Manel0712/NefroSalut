package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.LoginPaciente
import com.mariona.nefrosalut.models.Paciente
import kotlinx.coroutines.launch

class LogginPacienteViewModel: ViewModel() {
    private val _pacienteListLoading = MutableLiveData(false)
    public val pacienteListLoading: LiveData<Boolean> get() = _pacienteListLoading

    private val _paciente = MutableLiveData<List<Paciente>>(emptyList())
    public val paciente: LiveData<List<Paciente>> get() = _paciente

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun loggin(email: String, password: String) {
        viewModelScope.launch {
            _error.value = null
            var loginPaciente = LoginPaciente(email, password)
            var resposta = Connection.nefroSalutService.loggin(loginPaciente.email, loginPaciente.password)
            if (resposta.isSuccessful) {
                _paciente.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LogginPacienteViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return LogginPacienteViewModel() as T
    }
}