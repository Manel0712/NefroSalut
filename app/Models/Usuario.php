<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Usuario extends Model
{
    protected $common_fillable = [
        'nombre',
        'apellidos',
        'email',
        'telefono',
        'contraseña'
    ];
    public function __construct(array $attributes = [])
    {
        parent::__construct($attributes);
        $this->fillable = array_merge($this->fillable, $this->common_fillable);

    }
}
