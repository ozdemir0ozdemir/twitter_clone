import React from 'react';

interface TextInputProps {
    name: string;
    label: string;
    errorMessage: string;
    onChange(e: React.ChangeEvent<HTMLInputElement>): void;
    maxLength?: number;
    validator?(value: string): boolean;
};

const TextInput:React.FC<TextInputProps> = ({name,
                                                       label,
                                                       errorMessage,
                                                       onChange,
                                                       maxLength,
                                                       validator}) => {

    const [inputValue, setInputValue]
        = React.useState<string>("");

    const updateInput = (e: React.ChangeEvent<HTMLInputElement>) => {
        onChange(e);
        setInputValue(e.target.value);
    }

    return (
        <div className="text-input">
            <div>
                <span>{label}</span>
                <input name={name} onChange={updateInput} />
            </div>
        </div>
    );
};

export default TextInput;
