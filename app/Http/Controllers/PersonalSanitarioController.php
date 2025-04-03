<?php

namespace App\Http\Controllers;

use App\Models\PersonalSanitario;
use Illuminate\Http\Request;

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
        $PersonalSanitario = PersonalSanitario::create($request->all());
        return response()->json([
            $PersonalSanitario
        ], 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(Doctor $doctor)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Doctor $doctor)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Doctor $doctor)
    {
        //
    }
}
