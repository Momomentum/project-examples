<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

class File extends BaseModel
{

    protected $fillable = [
      'dataset_id', 'filename', 'original_filename', 'format', 'assign'
    ];


    public function dataset(){
        return $this->belongsTo('\App\Dataset');
    }

    public static function getFormats(){
        $format = DB::select(DB::raw('SHOW COLUMNS FROM files WHERE Field = "format"'))[0]->Type;
        preg_match('/^enum\((.*)\)$/', $format, $matches);
        $values = array();
        foreach(explode(',', $matches[1]) as $value){
            $values[] = trim($value, "'");
        }
        return $values;
    }

    public static function getAssigns(){
        $assigns = DB::select(DB::raw('SHOW COLUMNS FROM files WHERE Field = "assign"'))[0]->Type;
        preg_match('/^enum\((.*)\)$/', $assigns, $matches);
        $values = array();
        foreach(explode(',', $matches[1]) as $value){
            $values[] = trim($value, "'");
        }
        return $values;
    }

}
