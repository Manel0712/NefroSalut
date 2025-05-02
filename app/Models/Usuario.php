<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Progreso;
use App\Models\Dieta;
use App\Models\Paciente;
use App\Models\Familiar;

class Usuario extends Model
{
    protected $common_fillable = [
        'nombre',
        'apellidos',
        'email',
        'telefono',
        'password',
        'progreso_id'
    ];
    public function __construct(array $attributes = [])
    {
        parent::__construct($attributes);
        $this->fillable = array_merge($this->fillable, $this->common_fillable);

    }
    
    public function progreso()
    {
        return $this->belongsTo(Progreso::class);
    }

    public function dietas()
    {
        return $this->belongsToMany(Dieta::class, "paciente_dietas");
    }
}
