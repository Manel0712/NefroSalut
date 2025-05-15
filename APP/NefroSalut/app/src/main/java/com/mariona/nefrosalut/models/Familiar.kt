package com.mariona.nefrosalut.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Familiar (
    var id: Long,
    var nombre: String,
    var apellidos: String = "",
    var email: String,
    var telefono: String,
    var password: String,
    @SerializedName("paciente_id")
    var pacienteId: Int?
): Serializable