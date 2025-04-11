<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PacienteJuego extends Model
{
    protected $fillable = [
        "paciente_id",
        "quiz_id",
        "respuestas_correctas",
        "respuestas_incorrectas"
    ];
}
