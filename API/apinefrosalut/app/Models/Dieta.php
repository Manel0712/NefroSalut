<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Model\Usuario;
use App\Model\Plato;

class Dieta extends Model
{
    protected $fillable = [
        "nombre",
    ];

    public function usuarios()
    {
        return $this->belongsToMany(Usuario::class, "paciente_dietas");
    }

    public function platos()
    {
        return $this->belongsToMany(Plato::class, "dieta_platos");
    }
}
