@extends('app')

@section('content')

    <h1>Create Dataset</h1>

    <hr>


    {!! Form::open(['action' => 'DatasetsController@store']) !!}

    @include('datasets._form', ['submitButtonText' => 'Datensatz anlegen'])

    
    {!! Form::close() !!}

    @include('errors.list')

    

@stop