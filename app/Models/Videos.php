<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Videos extends Model
{
    protected $fillable = [
        "titulo",
        "estado_visualizacion",
        "introduccion",
        "detalle",
    ];
}
