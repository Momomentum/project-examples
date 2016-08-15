<div class="form-group">
    {!! Form::label('format', 'Format',  ['class' => 'control-label']) !!}
    {!! Form::select('format', $formats , null , ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('assign', 'Intern/Extern',  ['class' => 'control-label']) !!}
    {!! Form::select('assign', $assigns , null , ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('files', 'Dateien auswählen',  ['class' => 'control-label']) !!}
    {!! Form::file('files[]', ['class' => '', 'multiple' => true]) !!}
</div>

<div class="form-group">
    {!! Form::submit($submitButtonText, ['class' => 'btn btn-primary form-control']) !!}
</div>