<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Usuario;
use App\Models\Paciente;

class PersonalSanitario extends Usuario
{
    protected $fillable = [
        'rol',
        'identificador'
    ];

    public function pacientes()
    {
        return $this->hasMany(Paciente::class);
    }

    public function webAuthnCredentials()
    {
        return $this->hasMany(WebAuthnCredential::class, 'personal_sanitario_id');
    }
}
