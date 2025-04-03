<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;

class Paciente extends Usuario
{
    protected $fillable = [];

    public function __construct(array $attributes = []) {
        $this->fillable = array_merge(parent::$fillable, ['estado_animo, puntos, referente, DNI, fecha_nacimiento, peso, altura']);
        parent::__construct($attributes);
    }
}
