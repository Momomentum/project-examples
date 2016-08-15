@extends('app')

@section('content')

    <h1>Edit: {{ $dataset->title }}</h1>



    {!! Form::model($dataset, ['method' => 'PATCH', 'action' => ['DatasetsController@update', $dataset->id]]) !!}

    @include('datasets._form', ['submitButtonText' => 'Datensatz bearbeiten'])

    {!! Form::close() !!}


    @include('errors.list')


@stop