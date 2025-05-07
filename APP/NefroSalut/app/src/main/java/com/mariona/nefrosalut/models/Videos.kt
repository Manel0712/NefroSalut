package com.mariona.nefrosalut.models

import java.io.Serializable

data class Videos (
    val titulo: String = "",
    var link: String = "",
    var categoria: String = "",
    var categoria_id: Int
): Serializable

data class Categoria (
    val id: Int,
    val nombre: String
): Serializable