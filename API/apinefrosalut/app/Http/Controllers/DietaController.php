<?php

namespace App\Http\Controllers;

use App\Models\Dieta;
use App\Models\Familiar;
use App\Models\Paciente;
use App\Models\PacienteDieta;
use Illuminate\Http\Request;

class DietaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $dietas = Dieta::all();
        return response()->json($dietas, 200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $dieta = Dieta::create($request->all());
        $rol = $request->rol;
        $idUsuario = $request->id;
        if ($rol == "familiar") {
            $pacienteDieta = PacienteDieta::create([
                'dieta_id' => $dieta->id,
                'familiar_id' => $idUsuario,
            ]);
            return response()->json([
                $dieta,
                $pacienteDieta
            ], 201);
        }
        else if ($rol == "paciente") {
            $pacienteDieta = PacienteDieta::create([
                'dieta_id' => $dieta->id,
                'paciente_id' => $idUsuario,
            ]);
            return response()->json([
                $dieta,
                $pacienteDieta
            ], 201);
        }
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $dieta = Dieta::findOrFail($id);
        return response()->json([
            $dieta
        ], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $dieta = Dieta::findOrFail($id);
        $dieta->update($request->all());
        return response()->json([
            $dieta
        ], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $dieta = Dieta::findOrFail($id);
        $dieta->delete();
        return response()->json([], 204);
    }

    public function añadirPlato(string $idDieta, string $idPlato) {
        $dietaPlatos = DietaPlato::create([
            "dieta_id" => $idDieta,
            "plato_id" => $idPlato
        ]);
        return response()->json([
            $dietaPlatos
        ], 200);
    }

    public function platos(string $idDieta) {
        $dieta = Dieta::findOrFail($idDieta);
        $platos = $dieta->platos;
        return response()->json([
            $platos
        ], 200);
    }
}
