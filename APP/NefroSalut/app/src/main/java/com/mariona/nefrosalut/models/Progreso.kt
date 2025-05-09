package com.mariona.nefrosalut.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Progreso(
    var powerUps: Int,
    val monedas: Int,
    val puntos: Int
) : Serializable