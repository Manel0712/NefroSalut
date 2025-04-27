package com.mariona.nefrosalut.viewModels

import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import kotlinx.coroutines.launch
import android.graphics.Color
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Console
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.appcompat.app.AlertDialog
import com.mariona.nefrosalut.models.Paciente
import retrofit2.Response
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.LoginPaciente

class RegisterPacienteViewModel: ViewModel() {
    private val _pacienteListLoading = MutableLiveData(false)
    public val pacienteListLoading: LiveData<Boolean> get() = _pacienteListLoading

    private val _paciente = MutableLiveData<List<Paciente>>(emptyList())
    public val paciente: LiveData<List<Paciente>> get() = _paciente

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun register(paciente: Paciente) {
        viewModelScope.launch {
            _error.value = null

            try {
                var response = Connection.nefroSalutService.register(paciente.nombre, paciente.apellidos, paciente.email, paciente.telefono, paciente.contraseña.trim(), paciente.estadoEnfermedad, paciente.estadoAnimo, paciente.actividadFisica, paciente.diabetico, paciente.hipertenso, paciente.puntos, paciente.DNI, paciente.fechaNacimiento, paciente.peso, paciente.altura, paciente.IMC, paciente.clasificacion, paciente.progresoId)
                if (response.isSuccessful) {
                    _error.value = "Paciente registrado correctamente"
                }
            }
            catch (e: IOException) {
                _error.value = "Error de red"
            }
            catch (e: Exception) {
                _error.value = "Error desconocido: ${e.localizedMessage}"
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class RegisterPacienteViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return RegisterPacienteViewModel() as T
    }
}