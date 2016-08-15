<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateFilesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('files', function (Blueprint $table) {

            $table->engine='InnoDB';

            $table->increments('id');
            $table->integer('dataset_id')->unsigned();
            $table->string('filename');
            $table->string('original_filename');
            $table->enum('format', array('Document', 'Picture', 'Video', 'Data'));
            $table->enum('assign', array('Intern', 'Extern'));
            $table->timestamps();

          });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('files');
    }
}
