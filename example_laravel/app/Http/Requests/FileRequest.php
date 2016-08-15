<?php

namespace App\Http\Requests;

use App\Http\Requests\Request;

class FileRequest extends Request
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        $request = $this->instance()->all();
        $rules = [
            'format' => 'required',
            'assign' => 'required'
        ];
        $files = $request['files'];
        $files_rules = 'required';
        if (count($files) > 0) {
            foreach ($files as $key => $file) {
                $rules['files.'.$key] = $files_rules;
            }
        }
        return $rules;

    }

    protected function createMessages()
    {
        $request = $this->instance()->all();
        $messages = [];
        $files = $request['files'];
        if (count($files) > 0) {
            foreach ($files as $key => $image) {
                $messages['files.'.$key.'.required'] = 'Select files!';
            }
        }
        return $messages;
    }

    public function messages()
    {
        return $this->createMessages();
    }
}
