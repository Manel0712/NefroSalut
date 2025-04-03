<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Juego;

class Woordle extends Model
{
        protected $fillable = [];

    public function __construct(array $attributes = []) {
        $this->fillable = array_merge(parent::$fillable, ['correctWorld']);
        parent::__construct($attributes);
    }
}
