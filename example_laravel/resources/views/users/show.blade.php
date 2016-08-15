@extends('app')

@section('content')

    <h1>{!! $user->name !!}  </h1>

    Email: {!! $user->email !!}
    <br>

    Rights:

@stop
