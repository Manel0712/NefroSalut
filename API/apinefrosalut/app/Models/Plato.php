<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Dieta;

class Plato extends Model
{
    protected $fillable = [
        "nombre",
    ];

    public function dietas()
    {
        return $this->belongsToMany(Dieta::class, "dieta_platos");
    }
}
