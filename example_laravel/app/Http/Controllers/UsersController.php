<?php

namespace App\Http\Controllers;

use App\Http\Requests\UserRequest;
use App\User;
use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Redirect;

class UsersController extends Controller
{

    protected $redirectPath = '/users/index';

    public function index(){
        $users = \App\User::all();

        //return $users;
        return view('users.index', compact('users'));
    }

    public function show($id){
        $user = \App\User::findOrFail($id);
        return view('users.show', compact('user'));
    }

    public function create(){
        $departments = \App\Department::orderBy('id', 'ASC')->get();

        return view('users.create', compact('departments'));
    }





}
