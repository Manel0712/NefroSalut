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
import com.mariona.nefrosalut.models.Familiar
import com.mariona.nefrosalut.models.LoginPaciente

class RegisterFamiliarViewModel: ViewModel() {
    private val _pacienteListLoading = MutableLiveData(false)
    public val pacienteListLoading: LiveData<Boolean> get() = _pacienteListLoading

    private val _familiar = MutableLiveData<List<Familiar>>(emptyList())
    public val familiar: LiveData<List<Familiar>> get() = _familiar

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun register(familiar: Familiar) {
        viewModelScope.launch {
            _error.value = null

            try {
                var response = Connection.nefroSalutService.registerFamiliar(familiar.nombre, familiar.apellidos, familiar.email, familiar.telefono, familiar.password.trim())
                if (response.isSuccessful) {
                    _error.value = "Familiar registrado correctamente"
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
class RegisterFamiliarViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return RegisterFamiliarViewModel() as T
    }
}