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
        Schema::create('dieta_platos', function (Blueprint $table) {
            $table->unsignedBigInteger("dieta_id");
            $table->foreign('dieta_id')
                ->references('id')
                ->on('dietas');
            $table->unsignedBigInteger("plato_id");
            $table->foreign('plato_id')
                ->references('id')
                ->on('platos');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('dieta_platos');
    }
};
