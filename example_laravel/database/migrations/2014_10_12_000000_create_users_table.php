<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {

            $table->engine = 'InnoDB';

            $table->increments('id');
            $table->string('name');
            $table->string('email')->unique();
            $table->string('password', 60);
            $table->enum('CRM', array('no', 'r', 'w', 'rw'))->default('no');
            $table->enum('SCM', array('no', 'r', 'w', 'rw'))->default('no');
            $table->enum('PLM', array('no', 'r', 'w', 'rw'))->default('no');
            $table->enum('ORG', array('no', 'r', 'w', 'rw'))->default('no');
            $table->enum('GL', array('no', 'r', 'w', 'rw'))->default('no');
            $table->enum('PUBLIC', array('no', 'r', 'w', 'rw'))->default('r');
            $table->rememberToken();
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
        Schema::drop('users');
    }
}
