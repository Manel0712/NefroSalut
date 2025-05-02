<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('quiz_pacientes', function (Blueprint $table) {
            $table->unsignedBigInteger("quiz_id");
            $table->foreign('quiz_id')
                ->references('id')
                ->on('quizs');
            $table->unsignedBigInteger("paciente_id");
            $table->foreign('paciente_id')
                ->references('id')
                ->on('pacientes');
            $table->integer("respuestas_correctas");
            $table->integer("respuestas_incorrectas");
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('quiz_pacientes');
    }
};
