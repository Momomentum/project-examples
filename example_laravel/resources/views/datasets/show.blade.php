@extends('app')

@section('content')

    <div>
        <h1>{{ $dataset->title }}</h1>
        Author: {{ $dataset->author }}
        <br>
        Identnummer: {{ $dataset->ident }}
        <br>
        Status: {{ $dataset->state }}
    </div>


    <div>
        Files:

        @foreach($files as $file)
            <h2>
                <a href="{{ action('FilesController@download', $file->id) }}">{{ $file->original_filename }}</a>
            </h2>
        @endforeach
    </div>

    <div>
        <a href="{{ route('datasets.files.create', [$dataset->id]) }}" class="btn btn-default">Files hinzufügen</a>
    </div>

@stop
