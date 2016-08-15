<?php

namespace App\Http\Controllers;

use App\Dataset;
use App\Department;
use App\Http\Requests\DatasetRequest;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use PhpParser\Node\Expr\Array_;

class DatasetsController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth', ['except' => ['index','show']]);
    }

    public function index()
    {
        $datasets = Dataset::latest('updated_at')->get();
        return view('datasets.index', compact('datasets'));
    }

    public function show($id)
    {
        $dataset = Dataset::findOrFail($id);

        $files = $dataset->files()->get();

        return view('datasets.show', compact('dataset', 'files'));
    }

    public function create()
    {
        $departs = Department::orderBy('id', 'ASC')->get();
        $departments = array();
        foreach($departs as $depart){
           $departments[$depart['id']] = $depart['name'];
        }
        $sts = Dataset::getPossibleEnumValues('state');
        $states = array();
        foreach($sts as $state){
            $states[$state] = $state;
        }
        //return $departments;
        return view('datasets.create', compact('departments', 'states'));
    }

    public function store(DatasetRequest $request)
    {


        $dataset = new Dataset($request->all());

        return $dataset;
        //$dataset->user_id = \Auth::user()->id;
        //$dataset->save();


        //$user = \Auth::user()->datasets()->save($dataset);

        //return redirect()->route('datasets.show', array($dataset->id));

    }

    public function edit($id)
    {
        $dataset = Dataset::findOrFail($id);
        return view('datasets.edit', compact('dataset'));
    }

    public function update($id, DatasetRequest $request)
    {

        $input = $request->all();

        $dataset = Dataset::findOrFail($id);
        $dataset->department_name   = $input['department_name'];
        $dataset->title             = $input['title'];
        $dataset->author            = $input['author'];
        $dataset->ident             = $input['ident'];
        $dataset->state             = $input['state'];
        $dataset->description       = $input['description'];

        $dataset->save();

        return redirect('datasets');
    }

}
