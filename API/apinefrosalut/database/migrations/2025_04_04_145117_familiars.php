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
        Schema::create('familiars', function (Blueprint $table) {
            $table->id();
            $table->string('nombre');
            $table->string('apellidos');
            $table->string('email')->unique();
            $table->string('telefono');
            $table->text('contraseña');
            $table->unsignedBigInteger("paciente_id")->nullable();
            $table->foreign('paciente_id')
                ->references('id')
                ->on('pacientes');
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
        Schema::dropIfExists('familiars');
    }
};
