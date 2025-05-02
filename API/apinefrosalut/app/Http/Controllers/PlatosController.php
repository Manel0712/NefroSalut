<?php

namespace App\Http\Controllers;

use App\Models\Plato;
use Illuminate\Http\Request;

class PlatosController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $platos = Plato::all();
        return response()->json([
            $platos
        ], 200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $plato = Plato::create($request->all());
        return response()->json([
            $plato
        ], 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $plato = Plato::findOrFail($id);
        return response()->json([
            $plato
        ], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $plato = Plato::findOrFail($id);
        $plato->update($request->all());
        return response()->json([
            $plato
        ], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(platos $platos)
    {
        $plato = Plato::findOrFail($id);
        $plato->delete();
        return response()->json([], 204);
    }
}
