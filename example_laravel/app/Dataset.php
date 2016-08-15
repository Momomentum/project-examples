<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Dataset extends BaseModel
{
    protected $fillable = [
        'author',
        'ident',
        'state',
        'department_id',
        'title',
        'description',
    ];

    public function user(){
        return $this->belongsTo('\App\User');
    }

    public function department(){
        return $this->belongsTo('\App\Department');
    }

    public function files(){
        return $this->hasMany('\App\File');
    }
}
