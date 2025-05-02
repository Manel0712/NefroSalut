<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;

class Familiar extends Usuario
{
    protected $fillable = [
        'paciente_id'
    ];
}
