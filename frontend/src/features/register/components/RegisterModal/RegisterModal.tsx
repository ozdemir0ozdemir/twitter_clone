import React from 'react'

import {Modal} from "../../../../components/Modal/Modal";
import {RegistrationStepCounter} from '../RegisterStepCounter/RegistrationStepCounter';

import './RegisterModal.css';

export const RegisterModal: React.FC = () => {
    return (
        <Modal>
           <div className="register-modal">
                <RegistrationStepCounter step={1} />
           </div>
        </Modal>
    )
}