package com.mariona.nefrosalut.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mariona.nefrosalut.connections.Connection
import com.mariona.nefrosalut.models.Categoria
import com.mariona.nefrosalut.models.Videos
import kotlinx.coroutines.launch

class VideosViewModel: ViewModel() {
    private val _videosListLoading = MutableLiveData(false)
    public val videosListLoading: LiveData<Boolean> get() = _videosListLoading

    private var _video = MutableLiveData<List<Videos>>(emptyList())
    public val video: LiveData<List<Videos>> get() = _video

    private val _error = MutableLiveData<String?>(null)
    public val error: LiveData<String?> get() = _error

    public fun loadVideos() {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.videos()
            if (resposta.isSuccessful) {
                val lista = resposta.body()?.toMutableList() ?: mutableListOf()

                for (video in lista) {
                    val resposta2 = Connection.nefroSalutService.categoriaNombre(video.categoria_id.toString())
                    if (resposta2.isSuccessful) {
                        video.categoria = resposta2.body()!!.nombre
                    } else {
                        _error.value = "ERROR CODE: " + resposta2.code().toString()
                    }
                }

                _video.value = lista
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }

    public fun loadVideosCategoria(categoria: String) {
        viewModelScope.launch {
            _error.value = null
            var resposta = Connection.nefroSalutService.videosCategoria(categoria)
            if (resposta.isSuccessful) {
                val lista = resposta.body()?.toMutableList() ?: mutableListOf()

                for (video in lista) {
                    val resposta2 = Connection.nefroSalutService.categoriaNombre(video.categoria_id.toString())
                    if (resposta2.isSuccessful) {
                        video.categoria = resposta2.body()!!.nombre
                    } else {
                        _error.value = "ERROR CODE: " + resposta2.code().toString()
                    }
                }

                _video.value = lista
            }
            else {
                _error.value = "ERROR CODE: " + resposta.code().toString()
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class VideosViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return VideosViewModel() as T
    }
}