<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Department extends BaseModel
{

//    public function users(){
//        return $this->belongsToMany('\App\User');
//    }

    public function datasets(){
        return $this->hasMany('\App\Dataset');
    }


}
