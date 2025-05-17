package com.mariona.nefrosalut.models

import java.io.Serializable

data class Aliments (
    val id: Long,
    val nombre: String = "",
    val categoria: String = "",
    val clasificaciones: Map<String, Long> = emptyMap(),
): Serializable
