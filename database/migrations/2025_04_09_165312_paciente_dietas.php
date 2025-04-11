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
        Schema::create('paciente_dietas', function (Blueprint $table) {
            $table->unsignedBigInteger("dieta_id");
            $table->foreign('dieta_id')
                ->references('id')
                ->on('dietas');
            $table->unsignedBigInteger("paciente_id")->nullable();
            $table->foreign('paciente_id')
                ->references('id')
                ->on('pacientes');
            $table->unsignedBigInteger("familiar_id")->nullable();
            $table->foreign('familiar_id')
                ->references('id')
                ->on('familiars');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('paciente_dietas');
    }
};
