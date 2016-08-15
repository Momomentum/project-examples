<div class="form-group">

    {!! Form::label('title', 'Titel',  ['class' => 'control-label']) !!}
    {!! Form::text('title', null, ['class' => 'form-control']) !!}

</div>

<div class="form-group">
    {!! Form::label('author', 'Author', ['class' => 'control-label']) !!}
    {!! Form::text('author', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('ident', 'Identnummer', ['class' => 'control-label']) !!}
    {!! Form::text('ident', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('department_id', 'Bereich', ['class' => 'control-label']) !!}
    {!! Form::select('department_id', $departments , null , ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('state', 'Status', ['class' => 'control-label']) !!}
    {!! Form::select('state', $states , null , ['class' => 'form-control']) !!}
</div>



<div class="form-group">
    {!! Form::label('description', 'Beschreibung', ['class' => 'control-label']) !!}
    {!! Form::textarea('description', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::submit($submitButtonText, ['class' => 'btn btn-primary form-control']) !!}
</div>