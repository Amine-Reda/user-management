import React, {useState} from "react";

export function useFormFields<T>(initialState: T):[T, (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => void] {
    const [fields, setValues] = useState(initialState);

    return [
        fields,
        function (event:React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) {
            setValues({
                ...fields,
                [event.target.id]: event.target.value,
            });
        },
    ];
}