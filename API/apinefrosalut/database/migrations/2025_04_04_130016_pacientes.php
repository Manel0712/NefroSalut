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
        Schema::create('pacientes', function (Blueprint $table) {
            $table->id();
            $table->string('nombre');
            $table->string('apellidos');
            $table->string('email')->unique();
            $table->string('telefono');
            $table->text('password');
            $table->string('estado_enfermedad');
            $table->string('estado_animo');
            $table->boolean('actividad_fisica');
            $table->boolean('diabetico');
            $table->boolean('hipertenso');
            $table->string('estadio');
            $table->integer('puntos');
            $table->unsignedBigInteger("personal_sanitario_id")->nullable();
            $table->foreign('personal_sanitario_id')
                ->references('id')
                ->on('personal_sanitarios');
            $table->string("DNI", 9)->unique();
            $table->date("fecha_nacimiento");
            $table->double("peso");
            $table->double("altura");
            $table->integer("IMC");
            $table->string("clasificacion");
            $table->unsignedBigInteger("progreso_id");
            $table->foreign('progreso_id')
                ->references('id')
                ->on('progresos')
                ->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('pacientes');
    }
};
