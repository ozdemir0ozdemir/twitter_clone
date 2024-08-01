import React from 'react';
import TextInput from '../../../../components/TextInput/TextInput';

import './RegisterFormOne.css';

interface FormOneState {
    firstName: string;
    lastName: string;
    email: string;
    dateOfBirth: string;
}

export const RegisterFormOne: React.FC = () => {

    const [stepOneState, setStepOneState]
        = React.useState<FormOneState>({
            firstName: "",
            lastName: "",
            email: "",
            dateOfBirth: ""
        });

    const updateUser = (e: React.ChangeEvent<HTMLInputElement>): void => {
        setStepOneState({...stepOneState, [e.target.name]: e.target.value});
    };

    React.useEffect(()=>{
        console.log(`State changed: ${JSON.stringify(stepOneState)}`);
    },[stepOneState]);

    return (
        <div className="reg-step-one-container">
            <div className="reg-step-one-content">
                <TextInput name="firstName"
                           label="First Name:"
                           errorMessage="Please enter your name..."
                           onChange={updateUser} />

                <TextInput name="lastName"
                           label="Last Name:"
                           errorMessage="Please enter your last name..."
                           onChange={updateUser} />

                <TextInput name="email"
                           label="Email:"
                           errorMessage="Please enter a valid email..."
                           onChange={updateUser} />
            </div>
        </div>
    );
};