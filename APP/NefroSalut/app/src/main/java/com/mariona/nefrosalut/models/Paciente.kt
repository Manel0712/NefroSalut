package com.mariona.nefrosalut.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate

data class Paciente (
    var id: Long,
    var nombre: String,
    var apellidos: String = "",
    var email: String,
    var telefono: String,
    var password: String,
    @SerializedName("estado_enfermedad")
    var estadoEnfermedad: String,
    @SerializedName("estado_animo")
    var estadoAnimo: String = "",
    @SerializedName("actividad_fisica")
    var actividadFisica: Boolean,
    var diabetico: Boolean,
    var hipertenso: Boolean,
    var estadio: String,
    var puntos: Int,
    @SerializedName("personal_sanitario_id")
    var personalSanitarioId: Int?,
    var DNI: String,
    @SerializedName("fecha_nacimiento")
    var fechaNacimiento: String,
    var peso: Double,
    var altura: Double,
    var IMC: Double,
    var clasificacion: String,
    @SerializedName("progreso_id")
    var progresoId: Int,
): Serializable