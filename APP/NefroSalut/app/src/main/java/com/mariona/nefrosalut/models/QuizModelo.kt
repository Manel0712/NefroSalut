package com.mariona.nefrosalut.models

data class QuizModelo(
    val id: Int,
    val pregunta: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val correctOption: String,
    val categoria: String
)