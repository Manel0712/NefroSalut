<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PacienteDieta extends Model
{
    protected $fillable = [
        "dieta_id",
        "paciente_id",
        "familiar_id"
    ];
}
