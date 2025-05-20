package com.mariona.nefrosalut.models

data class QuizPaciente (
    var paciente_id: Long,
    var quiz_id: Long,
    var respuestas_correctas: Int,
    var respuestas_incorrectas: Int
)