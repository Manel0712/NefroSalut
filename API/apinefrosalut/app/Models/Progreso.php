<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;

class Progreso extends Model
{
    protected $fillable = [
        "power_ups",
        "monedas",
        "puntos"
    ];

    public function usuario()
    {
        return $this->hasOne(Usuario::class);
    }
}
