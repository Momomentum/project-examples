@extends('app')
@section('content')

    <div class="container" xmlns="http://www.w3.org/1999/html">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Create New User</div>
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" method="POST" action="{{ url('/register') }}">

                            @include('users._userform')

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

@stop
