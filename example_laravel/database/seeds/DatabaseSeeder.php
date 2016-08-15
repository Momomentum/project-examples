<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('departments')->delete();
        DB::table('departments')->insert([
          ['name' => 'CRM'],
            ['name' => 'SCM'],
            ['name' => 'PLM'],
            ['name' => 'ORG'],
            ['name' => 'GL'],
            ['name' => 'PUBLIC']
        ]);
    }
}

class UserSeeder extends Seeder
{
    /**
     * Run the Seeds for testusers
     *
     * @return void
     */
    public function run()
    {
      DB::table('users')->delete();
      DB::table('users')->insert([
          'name' => 'admin',
          'password' => 'admin123',
          'created_at' => Carbon\Carbon::now(),
          'updated_at' => Carbon\Carbon::now(),
          'email' => "admin@admin.de"
      ]);
    }
}

class TestSetSeeder extends Seeder
{
    /**
     * Run the Seedr for testusers
     *
     * @return void
     */
    public function run()
    {
        $user = App\User::first();
        DB::table('datasets')->delete();
        DB::table('datasets')->insert([

            'user_id' => $user['id'],
            'department_id' => 2,
            'title' => 'testtitle',
            'author' => 'testauthor1',
            'state' => 'In Arbeit',
            'ident' => '1234',
            'created_at' => Carbon\Carbon::now(),
            'updated_at' => Carbon\Carbon::now()
        ]);

        DB::table('datasets')->insert([
            'user_id' => $user['id'],
            'department_id' => 1,
            'title' => 'testtitle2',
            'author' => 'testauthor2',
            'state' => 'Aktuell',
            'ident' => '2222',
            'created_at' => Carbon\Carbon::now(),
            'updated_at' => Carbon\Carbon::now()
        ]);
    }
}
