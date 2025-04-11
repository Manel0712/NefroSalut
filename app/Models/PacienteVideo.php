<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PacienteVideo extends Model
{
    protected $fillable = [
        "paciente_id",
        "video_id",
        "num_visualizaciones",
        "estado_visualizacion",
    ];
}
