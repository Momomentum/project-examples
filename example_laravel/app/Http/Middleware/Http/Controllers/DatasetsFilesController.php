<?php

namespace App\Http\Controllers;

use App\Dataset;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\File;

class DatasetsFilesController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth', ['except' => ['index','show']]);
    }


    public function index($dataId){
        $files = \App\Dataset::find($dataId)->files()->get();
        //return 'here will be all files for Dataset number:' .  $dataId;
        return view('files.index', compact('files'));
    }

    public function show($dataId, $fileId){
        $file = \App\Dataset::find($dataId)->files()->find($fileId);
        return $file;
    }

    public function create($dataId){
        $enums = \App\File::getPossibleEnumValues('format');
        $formats = array();
        foreach($enums as $format){
            $formats[$format] = $format;
        }

        $enums = \App\File::getPossibleEnumValues('assign');
        $assigns = array();
        foreach($enums as $assign){
            $assigns[$assign] = $assign;
        }
        return view('files.create', compact('formats', 'assigns'))->with('dataId', $dataId);
    }

    public function store(){
        return 'File would be stored';
    }
}
