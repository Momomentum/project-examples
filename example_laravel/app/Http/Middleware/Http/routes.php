<?php

Route::group(['middleware' => ['web']], function () {

    Route::get('/', 'DatasetsController@index');

    Route::resource('datasets', 'DatasetsController');

    Route::resource('files', 'FilesController@index', ['only' => ['show', 'index']]);

    Route::resource('datasets.files', 'DatasetsFilesController');

    Route::auth();

    Route::get('/home', 'HomeController@index');

});



