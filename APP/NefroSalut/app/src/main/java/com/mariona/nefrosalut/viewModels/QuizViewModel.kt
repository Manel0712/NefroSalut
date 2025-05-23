package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.Progreso
import com.mariona.nefrosalut.models.QuizModelo
import com.mariona.nefrosalut.models.QuizPaciente
import com.google.gson.Gson
import kotlinx.coroutines.launch

class QuizViewModel: ViewModel() {
    private val _QuizListLoading = MutableLiveData(false)
    public val quizListLoading: LiveData<Boolean> get() = _QuizListLoading

    private val _quiz = MutableLiveData<List<QuizModelo>>(emptyList())
    public val quiz: LiveData<List<QuizModelo>> get() = _quiz

    private val _progreso = MutableLiveData<List<Progreso>>(emptyList())
    public val progreso: LiveData<List<Progreso>> get() = _progreso

    private val _guardarProgreso = MutableLiveData<List<QuizPaciente>>(emptyList())
    public val guardarProgreso: LiveData<List<QuizPaciente>> get() = _guardarProgreso

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun loadQuiz() {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.quiz()
            if (resposta.isSuccessful) {
                _quiz.value = resposta.body()
                _quiz.value?.let { original ->
                    val random = java.util.Random()
                    val desordenada = original
                        .map { it to random.nextInt(59) }
                        .sortedBy { it.second }
                        .map { it.first }
                        .toMutableList()

                    _quiz.value = desordenada
                }
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }

    public fun loadQuizCategoria(categoria: String) {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.quizCategoria(categoria)
            if (resposta.isSuccessful) {
                _quiz.value = resposta.body()
                _quiz.value?.let { original ->
                    val random = java.util.Random()
                    val desordenada = original
                        .map { it to random.nextInt(59) }
                        .sortedBy { it.second }
                        .map { it.first }
                        .toMutableList()

                    _quiz.value = desordenada
                }
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }

    public fun updateProgreso(idProgreso: Int, monedas: Int, popowerUps: Map<String, Int>, puntos: Int) {
        viewModelScope.launch {
            _error.value = null
            val gson = Gson()
            val powerUpsJson = gson.toJson(popowerUps)
            var resposta = Connection.nefroSalutService.updateProgreso(idProgreso, powerUpsJson, monedas, puntos)
            if (resposta.isSuccessful) {
                _progreso.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }

    public fun guardarProgreso(idUsuario: Long, respuestasCorectas: Int, respuestasIncorrectas: Int) {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.guardarPartida(idUsuario, 1, respuestasCorectas, respuestasIncorrectas)
            if (resposta.isSuccessful) {
                _guardarProgreso.value = resposta.body()
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class QuizViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel() as T
    }
}