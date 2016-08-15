<div class="form-group">
    {!! Form::label('name', 'Name',  ['class' => 'control-label']) !!}
    {!! Form::text('name', '', ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('email', 'Email',  ['class' => 'control-label']) !!}
    {!! Form::text('email', '', ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('password', 'Password',  ['class' => 'control-label']) !!}
    {!! Form::password('password', ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('password_confirmation', 'Confirm Password',  ['class' => 'control-label']) !!}
    {!! Form::password('password_confirmation', ['class' => 'form-control']) !!}
</div>

<div>
    Rights:
    <br>
    @foreach($departments as $department)
        <div>
            {!! $department->name !!}:
            {!! Form::select($department->name, array('no' => 'none', 'r' => 'read', 'w' => 'write', 'rw' => 'read/write') , null , ['class' => 'form-control']) !!}
        </div>
    @endforeach
</div>

<div class="form-group">
    {!! Form::submit($submitButtonText, ['class' => 'btn btn-primary form-control']) !!}
</div>