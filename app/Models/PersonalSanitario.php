<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;

class PersonalSanitario extends Usuario
{
    protected $fillable = [
        'rol',
        'identificador'
    ];
}
