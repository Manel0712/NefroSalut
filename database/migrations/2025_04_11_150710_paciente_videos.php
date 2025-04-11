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
        Schema::create('paciente_videos', function (Blueprint $table) {
            $table->unsignedBigInteger("videos_id");
            $table->foreign('videos_id')
                ->references('id')
                ->on('videos');
            $table->unsignedBigInteger("paciente_id");
            $table->foreign('paciente_id')
                ->references('id')
                ->on('pacientes');
            $table->integer("num_visualizaciones");
            $table->integer("estado_visualizacion");
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
