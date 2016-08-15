<?php

namespace App\Http\Controllers;

use App\File;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class FilesController extends Controller
{
    public function index(){
        $files = File::latest('updated_at')->get();
        return view('files.index', compact('files'));
    }

    public function show($id){
        $file = File::findOrFail($id);
        return view('files.show', compact('file'));
    }
}
