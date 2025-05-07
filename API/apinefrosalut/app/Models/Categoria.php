<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Video;

class Categoria extends Model
{
    protected $fillable = [
        'nombre'
    ];

    public function videos()
    {
        return $this->hasMany(Video::class);
    }
}
