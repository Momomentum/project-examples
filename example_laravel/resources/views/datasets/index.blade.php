@extends('app')

@section('content')

    <h1>Datasets</h1>

    @foreach($datasets as $dataset)
        <h2>
            <a href="{{ action('DatasetsController@show', [$dataset->id]) }}">{{ $dataset->title }}</a>
        </h2>
        Author: {{ $dataset->author }}
        <br>
        Identnummer: {{ $dataset->ident }}
        <br>
        Status: {{ $dataset->state }}
        <hr>
    @endforeach

@stop
