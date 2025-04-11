<?php

namespace App\Http\Controllers;

use App\Models\Familiar;
use App\Models\Progreso;
use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;

class FamiliarController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $familiares = Familiar::all();
        return response()->json([
            $familiares
        ], 200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $password = $request->contraseña;
        $passwordHash = Hash::make($password);
        $progreso = Progreso::create([
            'power_ups' => json_encode([]),
            'monedas' => 0,
            'puntos' => 0
        ]);
        $familiar = Familiar::create([
            'nombre' => $request->nombre,
            'apellidos' => $request->apellidos,
            'email' => $request->email,
            'telefono' => $request->telefono,
            'contraseña' => $passwordHash,
            'progreso_id' => $progreso->id
        ]);
        return response()->json([
            $familiar
        ], 201);
    }

    /**
     * Display the specified resource.
     */
    public function asignarPaciente(string $idFamiliar, string $idPaciente)
    {
        $familiar = Familiar::findOrFail($idFamiliar);
        $familiar->update([
            "paciente" => $idPaciente
        ]);
        return response()->json([
            $familiar
        ], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $familiar = Familiar::findOrFail($id);
        $familiar->update($request->all());
        return response()->json([
            $familiar
        ], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $familiar = Familiar::findOrFail($id);
        $familiar->delete();
        return response()->json([], 204);
    }

    public function consultarProgreso(string $idPersonal) {
        $personal = Familiar::findOrFail($idPersonal);
        $progreso = $personal->progreso;
        return response()->json([
            $progreso
        ], 200);
    }

    public function asignarDieta(string $idFamiliar, string $idDieta) {
        $pacienteDieta = PacienteDieta::create([
            'dieta_id' => $idDieta,
            'familiar_id' => $idFamiliar,
        ]);
        return response()->json([
            $pacienteDieta
        ], 200);
    }

    public function dietas(string $id) {
        $familiar = Familiar::findOrFail($id);
        $dietas = $familiar->dietas;
        return response()->json([
            $dietas
        ], 200);
    }

    public function loggin(Request $request) {
        $familiar = Familiar::where('email', $request->email)->get();
        if ($familiar && Hash::check($request->password, $paciente->password)) {
            return response()->json([
                $familiar
            ], 200);
        }
        else {
            return response()->json([
                "Email i/o password incorrectos"
            ], 401);
        }
    }
}
