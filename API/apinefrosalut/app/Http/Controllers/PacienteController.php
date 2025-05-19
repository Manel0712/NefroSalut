<?php

namespace App\Http\Controllers;

use App\Models\Paciente;
use App\Models\Progreso;
use App\Models\PacienteDieta;
use App\Models\PacienteJuego;
use App\Models\PacienteVideo;
use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;

class PacienteController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $pacientes = Paciente::all();
        return response()->json([
            $pacientes
        ], 200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $password = $request->password;
        $passwordHash = Hash::make($password);
        $progreso = Progreso::create([
            'power_ups' => json_encode([]),
            'monedas' => 0,
            'puntos' => 0
        ]);
        $Paciente = Paciente::create([
            'nombre' => $request->nombre,
            'apellidos' => $request->apellidos,
            'email' => $request->email,
            'telefono' => $request->telefono,
            'password' => $passwordHash,
            'estado_enfermedad' => $request->estado_enfermedad,
            'estado_animo' => $request->estado_animo,
            'actividad_fisica' => filter_var($request->actividad_fisica, FILTER_VALIDATE_BOOLEAN),
            'diabetico' => filter_var($request->diabetico, FILTER_VALIDATE_BOOLEAN),
            'hipertenso' => filter_var($request->hipertenso, FILTER_VALIDATE_BOOLEAN),
            'estadio' => $request->estadio,
            'puntos' => $request->puntos,
            'personal_sanitario_id' => $request->personal_sanitario_id,
            'DNI' => $request->DNI,
            'fecha_nacimiento' => $request->fecha_nacimiento,
            'peso' => $request->peso,
            'altura' => $request->altura,
            'IMC' => $request->IMC,
            'clasificacion' => $request->clasificacion,
            'progreso_id' => $progreso->id
        ]);
        return response()->json([
            $Paciente
        ], 201);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $paciente = Paciente::findOrFail($id);
        $paciente->update($request->all());
        return response()->json([
            $paciente
        ], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $paciente = Paciente::findOrFail($id);
        $paciente->delete();
        return response()->json([], 204);
    }

    public function consultarProgreso(string $id) {
        $paciente = Paciente::findOrFail($id);
        $progreso = $paciente->progreso;
        return response()->json([
            $progreso
        ], 200);
    }

    public function asignarDieta(string $idPaciente, string $idDieta) {
        $pacienteDieta = PacienteDieta::create([
            'dieta_id' => $idDieta,
            'paciente_id' => $idPaciente,
        ]);
        return response()->json([
            $pacienteDieta
        ], 200);
    }

    public function dietas(string $id) {
        $paciente = Paciente::findOrFail($id);
        $dietas = $paciente->dietas;
        return response()->json([
            $dietas
        ], 200);
    }

    public function guardarPartida(string $idPaciente, string $idQuiz, int $correctas, int $incorrectas) {
        $partida = PacienteJuego::create([
            "paciente_id" => $idPaciente,
            "quiz_id" => $idQuiz,
            "respuestas_correctas" => $correctas,
            "respuestas_incorrectas" => $incorrectas
        ]);
        return response()->json([
            $partida
        ], 200);
    }

    public function consultarPartidaAnterior(string $idPaciente) {
        $paciente = Paciente::findOrFail($idPaciente);
        $partidas = $paciente->quizs;
        return response()->json([
            $partidas
        ], 200);
    }

    public function verVideo(string $idPaciente, string $idVideo, int $numVisualizaciones, string $estadoVisualizacion) {
        $visualizacion = PacienteVideo::create([
            "paciente_id" => $idPaciente,
            "video_id" => $idVideo,
            "num_visualizaciones" => $numVisualizaciones,
            "estado_visualizacion" => $estadoVisualizacion,
        ]);
        return response()->json([
            $visualizacion
        ], 200);
    }

    public function loggin(Request $request) {
        $paciente = Paciente::where('email', $request->email)->first();
        if ($paciente && Hash::check($request->password, $paciente->password)) {
            return response()->json([
                $paciente
            ], 200);
        }
        else {
            return response()->json([
                "Email i/o password incorrectos"
            ], 401);
        }
    }
}
