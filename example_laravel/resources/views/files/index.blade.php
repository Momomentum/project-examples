@extends('app')

@section('content')

    <h1>Files</h1>
    @foreach($files as $file)
        <h2>
            <a href="{{ action('FilesController@download', $file->filename) }}">{{ $file->original_filename }}</a>
        </h2>
    @endforeach

@stop
