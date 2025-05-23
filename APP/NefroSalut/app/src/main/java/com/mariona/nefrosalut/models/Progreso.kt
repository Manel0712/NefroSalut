package com.mariona.nefrosalut.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Progreso(
    @SerializedName("power_ups")
    val powerUps: Map<String, Int>,
    val monedas: Int,
    val puntos: Int
)

