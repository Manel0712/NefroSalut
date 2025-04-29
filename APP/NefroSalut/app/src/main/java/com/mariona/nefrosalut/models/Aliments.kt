package com.mariona.nefrosalut.models

import java.io.Serializable

data class Aliments (
    val alimento: String = "",
    val categoria: String = "",
    val clasificaciones: Map<String, Long> = emptyMap(),
): Serializable
