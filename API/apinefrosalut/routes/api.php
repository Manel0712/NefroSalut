<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PersonalSanitarioController;
use App\Http\Controllers\PacienteController;
use App\Http\Controllers\FamiliarController;
use App\Http\Controllers\DietaController;
use App\Http\Controllers\PlatosController;
use App\Http\Controllers\QuizController;
use App\Http\Controllers\VideoController;
use App\Http\Controllers\WebAuthnController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::apiResource('/personal', PersonalSanitarioController::class)->middleware('auth:sanctum');
Route::apiResource('/paciente', PacienteController::class)->middleware('auth:sanctum');
Route::apiResource('/familiar', FamiliarController::class)->middleware('auth:sanctum');
Route::apiResource('/dieta', DietaController::class)->middleware('auth:sanctum');
Route::apiResource('/plato', PlatosController::class)->middleware('auth:sanctum');
Route::apiResource('/quiz', QuizController::class)->middleware('auth:sanctum');
Route::apiResource('/video', VideosController::class)->middleware('auth:sanctum');

Route::get('/personal/{personal}/pacientes', [PersonalSanitarioController::class, "pacientes"])->middleware('auth:sanctum');
Route::get('/personal/{personal}/asignarpaciente/{paciente}', [PersonalSanitarioController::class, "asignarPaciente"])->middleware('auth:sanctum');
Route::get('/personal/{personal}/progreso', [PersonalSanitarioController::class, "consultarProgreso"])->middleware('auth:sanctum');
Route::post('/personal/loggin', [PersonalSanitarioController::class, "loggin"])->middleware('auth:sanctum');
Route::get('/paciente/{paciente}/asignardieta', [PacienteController::class, "asignarDieta"])->middleware('auth:sanctum');
Route::get('/paciente/{paciente}/dietas', [PacienteController::class, "dietas"])->middleware('auth:sanctum');
Route::get('/paciente/guardarpartida/{paciente}/{quiz}/{correctas}/{incorrectas}', [PacienteController::class, "guardarPartida"])->middleware('auth:sanctum');
Route::get('/paciente/consultarpartida/{paciente}', [PacienteController::class, "consultarPartidaAnterior"])->middleware('auth:sanctum');
Route::get('/paciente/{paciente}/{video}/{numVisualizaciones}/{estadoVisualizacion}', [PacienteController::class, "verVideo"])->middleware('auth:sanctum');
Route::post('/paciente/loggin', [PacienteController::class, "loggin"])->middleware('auth:sanctum');
Route::get('/familiar/{familiar}/asignarpaciente/{paciente}', [FamiliarController::class, "asignarPaciente"])->middleware('auth:sanctum');
Route::get('/familiar/{familiar}/asignardieta', [FamiliarController::class, "asignarDieta"])->middleware('auth:sanctum');
Route::get('/familiar/{familiar}/dietas', [FamiliarController::class, "dietas"])->middleware('auth:sanctum');
Route::post('/familiar/loggin', [FamiliarController::class, "loggin"])->middleware('auth:sanctum');
Route::get('/dieta/{dieta}/añadirplato', [DietaController::class, "añadirPlato"])->middleware('auth:sanctum');
Route::get('/dieta/{dieta}/platos', [DietaController::class, "platos"])->middleware('auth:sanctum');

/* Token: sxqiboygTluUzvaBgSRSUxtGYv1VctKFPhuGb2Gyaaa195ca */