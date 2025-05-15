package com.mariona.nefrosalut.models

import java.io.Serializable

data class Dietas (
    var id: Long?,
    var nombre: String,
    var rol: String?,
    var usuario: Long?
): Serializable