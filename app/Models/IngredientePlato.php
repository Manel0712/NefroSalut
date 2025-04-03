<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class IngredientePlato extends Model
{
    protected $fillable = [
        "ingrediente",
        "plato"
    ];
}
