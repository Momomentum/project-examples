<?php

namespace App\Http\Controllers;

use App\Http\Requests\FileRequest;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\File;
use Illuminate\Support\Facades\Storage;

class DatasetsFilesController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth');
    }


    public function index($dataId){
        $files = \App\Dataset::find($dataId)->files()->get();
        //return 'here will be all files for Dataset number:' .  $dataId;
        return view('files.index', compact('files'));
    }

    public function show($dataId, $fileId){
        $file = \App\Dataset::find($dataId)->files()->find($fileId);
        return $file->original_filename;
        //return $file;
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

    public function store($dataId, FileRequest $request){

        $files = $request->file('files');
        $file_names = '';
        foreach($files as $file){
            $extension = $file->getClientOriginalExtension();
            Storage::disk('local')->put($file->getFilename().'.'.$extension,  File::get($file));
            $entry = new \App\File;
            //$entry->mime = $file->getClientMimeType();
            $entry->original_filename = $file->getClientOriginalName();
            $entry->filename = $file->getFilename().'.'.$extension;
            $entry->format = $request->get('format');
            $entry->assign = $request->get('assign');
            $entry->dataset_id = $dataId;
            $entry->save();
        }
        return redirect()->route('datasets.show', [$dataId]);
    }
}
