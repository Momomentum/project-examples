@extends('app')

@section('content')

    <h2>Search in Datasets</h2>
    {!! Form::open(['action' => 'DatasetsController@index', 'method' => 'get']) !!}

    @include('searches._search', ['searchButtonText' => 'Suchen'])


    {!! Form::close() !!}

    @include('errors.list')

@stop