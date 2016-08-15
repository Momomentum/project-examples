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
    {!! Form::label('dateFrom', 'Von', ['class' => 'control-label']) !!}
    <input type="date" name="dateFrom" class="form-control">
</div>

<div class="form-group">
    {!! Form::label('dateTo', 'Bis', ['class' => 'control-label']) !!}
    <input type="date" name="dateTo" class="form-control">
</div>

<div class="form-group">
    {!! Form::label('desc1', 'Beschreibung1', ['class' => 'control-label']) !!}
    {!! Form::text('desc1', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('desc2', 'Beschreibung2', ['class' => 'control-label']) !!}
    {!! Form::text('desc2', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    {!! Form::label('desc3', 'Beschreibung3', ['class' => 'control-label']) !!}
    {!! Form::text('desc3', null, ['class' => 'form-control']) !!}
</div>

<div class="form-group">
    <input class="btn btn-primary form-control" type="submit" name="search" value="Suchen">
</div>