@extends('app')

@section('content')

    <h1>Users</h1>
    @foreach($users as $user)
        <h2>
            <a href="{{ action('UsersController@show', $user->id) }}">{{ $user->name }}</a>
        </h2>
    @endforeach

@stop
