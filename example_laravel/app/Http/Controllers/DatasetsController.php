<?php

namespace App\Http\Controllers;

use App\Dataset;
use App\Department;
use App\Http\Requests\DatasetRequest;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Http\Requests\SearchRequest;
use App\User;
use Illuminate\Support\Facades\Auth;
use PhpParser\Node\Expr\Array_;

class DatasetsController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index(SearchRequest $request)
    {


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



        if(isset($_GET['search']))
        {
            $datasets = Dataset::latest('updated_at')->whereNested(function($query) use ($request){
                $query->where('title', 'LIKE', '%'.$request->title.'%');
                $query->where('author', 'LIKE', '%'.$request->author.'%');
                if($request->ident != ''){
                    $query->where('ident', '=', $request->ident);
                }
                if($request->department_id != 0){
                    $query->where('department_id', '=', $request->department_id);
                }
                if($request->state !== 'All'){
                    $query->where('state', '=', $request->state);
                }
                $query->where('description', 'LIKE', '%'.$request->desc1.'%');
                $query->where('description', 'LIKE', '%'.$request->desc2.'%');
                $query->where('description', 'LIKE', '%'.$request->desc3.'%');
                if($request->dateFrom !== ''){
                    $query->where('updated_at', '>=', $request->dateFrom);
                }
                if($request->dateTo !== ''){
                    $query->where('updated_at', '<=', $request->dateTo);
                }

            })->whereIn('department_id', $rights)->get();
        }
        else
        {
            $datasets = Dataset::latest('updated_at')->whereIn('department_id', $rights)->get();
        }

        return view('datasets.index', compact('datasets', 'departments', 'states'));
    }

    public function show($id)
    {
        $dataset = Dataset::findOrFail($id);

        $files = $dataset->files()->get();

        return view('datasets.show', compact('dataset', 'files'));
    }

    public function create()
    {
        $rights = array();

        if(Auth::user()->CRM == 'r' || Auth::user()->CRM == 'rw' || Auth::user()->CRM == 'w'){
            $rights[0] = \App\Department::all()->where('name', 'CRM')->first()->id;
        }
        if(Auth::user()->SCM == 'r' || Auth::user()->SCM == 'rw' || Auth::user()->SCM == 'w'){
            $rights[1] = \App\Department::all()->where('name', 'SCM')->first()->id;
        }
        if(Auth::user()->PLM == 'r' || Auth::user()->PLM == 'rw' || Auth::user()->PLM == 'w'){
            $rights[2] = \App\Department::all()->where('name', 'PLM')->first()->id;
        }
        if(Auth::user()->ORG == 'r' || Auth::user()->ORG == 'rw' || Auth::user()->ORG == 'w'){
            $rights[3] = \App\Department::all()->where('name', 'ORG')->first()->id;
        }
        if(Auth::user()->GL == 'r' || Auth::user()->GL == 'rw' || Auth::user()->GL == 'w'){
            $rights[4] = \App\Department::all()->where('name', 'GL')->first()->id;
        }
        if(Auth::user()->PUBLIC == 'r' || Auth::user()->PUBLIC == 'rw' || Auth::user()->PUBLIC == 'w'){
            $rights[5] = \App\Department::all()->where('name', 'PUBLIC')->first()->id;
        }

        $departs = Department::orderBy('id', 'ASC')->whereIn('id', $rights)->get();
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

        //return $dataset;
        $dataset->user_id = \Auth::user()->id;
        $dataset->save();


        $user = \Auth::user()->datasets()->save($dataset);

        return redirect()->route('datasets.show', array($dataset->id));

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

    public function search()
    {

        $departs = Department::orderBy('id', 'ASC')->get();
        $departments = array();
        $departments[0] = 'All';
        foreach($departs as $depart){
            $departments[$depart['id']] = $depart['name'];
        }
        $sts = Dataset::getPossibleEnumValues('state');
        $states = array();
        $states['All'] = 'All';
        foreach($sts as $state){
            $states[$state] = $state;
        }
        //return $departments;
        return view('datasets.search', compact('departments', 'states'));    }


}
