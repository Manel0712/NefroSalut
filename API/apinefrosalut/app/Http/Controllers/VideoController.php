<?php

namespace App\Http\Controllers;

use App\Models\Video;
use App\Models\Categoria;
use Illuminate\Http\Request;

class VideoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $videos = Video::all();
        return response()->json($videos, 200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $video = Video::create($request->all());
        return response()->json([$video], 201);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        $video = Video::findOrFail($id);
        return response()->json([
            $video
        ], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $video = Video::findOrFail($id);
        $video->update($request->all());
        return response()->json([
            $video
        ], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $video = Video::findOrFail($id);
        $video->delete();
        return response()->json([], 204);
    }

    public function videosCategoria(string $nombre) {
        $categoria = Categoria::where('nombre', $nombre)->first();
        $videos = $categoria->videos;
        $finalResult = [];
        if ($videos->count()==1) {
            foreach ($videos as $item) {
                $finalResult[] = $item;
            }
            return response()->json($finalResult, 200);
        }
        else {
            foreach ($videos as $item) {
                $finalResult[] = $item;
            }
            return response()->json($finalResult, 200);
        }
    }
}
