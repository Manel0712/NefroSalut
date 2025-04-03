<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Juego;

class Quiz extends Model
{
    protected $fillable = [];

    public function __construct(array $attributes = []) {
        $this->fillable = array_merge(parent::$fillable, ['pregunta, option1, option2, option3, option4, correctOption']);
        parent::__construct($attributes);
    }
}
