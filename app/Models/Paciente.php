<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;
use App\Models\PersonalSanitario;
use App\Models\Quizs;
use App\Models\Video;

class Paciente extends Usuario
{
    protected $fillable = [
        'estado_enfermedad',
        'estado_animo',
        'actividad_fisica',
        'diabetico',
        'hipertenso',
        'estadio',
        'puntos',
        'personal_sanitario_id',
        'DNI',
        'fecha_nacimiento',
        'peso',
        'altura',
        'IMC',
        'clasificacion',
    ];

    public function personal_sanitario()
    {
        return $this->belongsTo(PersonalSanitario::class);
    }

    public function quizs()
    {
        return $this->belongsToMany(Quiz::class, "quiz_pacientes");
    }

    public function videos()
    {
        return $this->belongsToMany(Video::class, "paciente_videos");
    }
}
