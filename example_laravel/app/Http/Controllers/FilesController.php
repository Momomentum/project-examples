<?php

namespace App\Http\Controllers;

use App\Dataset;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Auth;

class FilesController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index(){
        $rights = array();

        if(Auth::user()->CRM == 'r' || Auth::user()->CRM == 'rw'){
            $rights[0] = \App\Department::all()->where('name', 'CRM')->first()->id;
        }
        if(Auth::user()->SCM == 'r' || Auth::user()->SCM == 'rw'){
            $rights[1] = \App\Department::all()->where('name', 'SCM')->first()->id;
        }
        if(Auth::user()->PLM == 'r' || Auth::user()->PLM == 'rw'){
            $rights[2] = \App\Department::all()->where('name', 'PLM')->first()->id;
        }
        if(Auth::user()->ORG == 'r' || Auth::user()->ORG == 'rw'){
            $rights[3] = \App\Department::all()->where('name', 'ORG')->first()->id;
        }
        if(Auth::user()->GL == 'r' || Auth::user()->GL == 'rw'){
            $rights[4] = \App\Department::all()->where('name', 'GL')->first()->id;
        }
        if(Auth::user()->PUBLIC == 'r' || Auth::user()->PUBLIC == 'rw'){
            $rights[5] = \App\Department::all()->where('name', 'PUBLIC')->first()->id;
        }

        $files = \App\File::latest('updated_at')->whereHas('dataset', function ($query) use ($rights){
            $query->whereIn('department_id', $rights);
        })->get();
        return view('files.index', compact('files'));
    }

    public function show($id){
        $file = \App\File::findOrFail($id);

        return view('files.show', compact('file'));
    }

    public function download($id){
        $file = \App\File::find($id);
        $filename = $file->filename;
        $original_filename = $file->original_filename;

        // Check if file exists in app/storage/file folder

        $file_path = storage_path(). '/app/' . $filename;
        if (file_exists($file_path))
        {
            // Send Download
            return response()->download($file_path, $original_filename, [
                'Content-Length: '. filesize($file_path)
            ]);
        }
        else
        {
            exit('Requested file does not exist on our server!');
        }
    }
}
