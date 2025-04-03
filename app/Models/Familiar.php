<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;

class Familiar extends Usuario
{
    protected $fillable = [];

    public function __construct(array $attributes = []) {
        $this->fillable = array_merge(parent::$fillable, ['paciente']);
        parent::__construct($attributes);
    }
}
