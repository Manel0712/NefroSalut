<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class DietaPlato extends Model
{
    protected $fillable = [
        "dieta_id",
        "plato_id"
    ];
}
