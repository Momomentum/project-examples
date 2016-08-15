@extends('app')
@section('content')

    <h1>Upload Files</h1>

    <hr>


    {!! Form::open(['action' => array('DatasetsFilesController@store', $dataId), 'files' => true]) !!}

    @include('files._form', ['submitButtonText' => 'Files hochladen'])

    {!! Form::close() !!}

    @include('errors.list')

@stop
