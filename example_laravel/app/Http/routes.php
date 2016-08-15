<?php

Route::group(['middleware' => ['web']], function () {

    Route::resource('datasets.files', 'DatasetsFilesController');

    Route::get('datasets/search', ['as' => 'search' , 'uses' => 'DatasetsController@search']);

    Route::resource('datasets', 'DatasetsController');

    Route::resource('files', 'FilesController', ['only' => ['show', 'index']]);

    Route::resource('users', 'UsersController');

    Route::auth();

    Route::get('/home', 'HomeController@index');

    Route::get('/', 'DatasetsController@index');

    Route::get('download/{id}', 'FilesController@download'); //->where('filename', '[A-Za-z0-9\-\_\.]+');

});



