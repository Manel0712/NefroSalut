<?php

namespace App\Http\Controllers;

use App\Models\PersonalSanitario;
use App\Models\Paciente;
use App\Models\Progreso;
use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;
use PragmaRX\Google2FA\Google2FA;
use Base32\Base32;

class PersonalSanitarioController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $PersonalSanitario = PersonalSanitario::all();
        return response()->json([
            $PersonalSanitario
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
        $PersonalSanitario = PersonalSanitario::create([
            'nombre' => $request->nombre,
            'apellidos' => $request->apellidos,
            'email' => $request->email,
            'telefono' => $request->telefono,
            'password' => $passwordHash,
            'rol' => $request->rol,
            'identificador' => $request->identificador,
            'progreso_id' => $progreso->id
        ]);
        return response()->json([
            $PersonalSanitario
        ], 201);
    }

    /**
     * Display the specified resource.
     */
    public function pacientes(string $id)
    {
        $personal = PersonalSanitario::findOrFail($id);
        $misPacientes = $personal->pacientes;
        return response()->json([
            $misPacientes
        ], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $personal = PersonalSanitario::findOrFail($id);
        $personal->update($request->all());
        return $response()->json([
            $personal
        ], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $personal = PersonalSanitario::findOrFail($id);
        $personal->delete();
        return response()->json([], 204);
    }

    public function asignarPaciente(string $idPersonal, string $idPaciente) {
        $paciente = Paciente::findOrFail($idPaciente);
        $paciente->update([
            "personal_sanitario_id" => $idPersonal
        ]);
        return response()->json([
            $paciente
        ], 200);
    }

    public function consultarProgreso(string $idPersonal) {
        $personal = PersonalSanitario::findOrFail($idPersonal);
        $progreso = $personal->progreso;
        return response()->json([
            $progreso
        ], 200);
    }

    public function loggin(Request $request) {
        $personal = PersonalSanitario::where('email', $request->email)->first();
        dd($personal);
        if ($personal && Hash::check($request->password, $personal->password)) {
            return response()->json([
                $personal
            ], 200);
        }
        else {
            return response()->json([
                "Email i/o password incorrectos"
            ], 401);
        }
    }
}
