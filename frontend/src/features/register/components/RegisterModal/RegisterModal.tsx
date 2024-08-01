import React, {useState} from 'react'

import {Modal} from "../../../../components/Modal/Modal";
import {RegistrationStepCounter} from '../RegisterStepCounter/RegistrationStepCounter';
import {DetermineModalContent} from "../../utils/RegisterModalUtils";

import './RegisterModal.css';

export const RegisterModal: React.FC = () => {

    const [step, setStep] = useState<number>(1);

    const stepButtonClicked = () => {
        step === 1 || step === 4 || step >= 6 ? setStep(step) : setStep(step-1);
    }

    return (
        <Modal>
           <div className="register-modal">
                <RegistrationStepCounter step={step} changeStep={stepButtonClicked}/>
               <div className="register-modal-content">
                {DetermineModalContent(step)}
               </div>
           </div>
        </Modal>
    )
}