import React from 'react';
import TextInput from '../../../../components/TextInput/TextInput';
import {ValidatedInput} from "../../../../components/ValidatedInput/ValidatedInput";

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

                <ValidatedInput name="firstName"
                                label="First"
                                errorMessage="What's your name?"
                                validator={() => true}
                                changeValue={updateUser} />

                <ValidatedInput name="lastName"
                                label="Last"
                                errorMessage="What's your name?"
                                validator={() => true}
                                changeValue={updateUser} />

                <ValidatedInput name="email"
                                label="Email"
                                errorMessage="Please enter a valid email."
                                validator={() => true}
                                changeValue={updateUser} />
            </div>
        </div>
    );
};