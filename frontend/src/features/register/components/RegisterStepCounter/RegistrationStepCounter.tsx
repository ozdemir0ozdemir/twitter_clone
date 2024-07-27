import React from "react";

import {displayIcon} from '../../utils/RegisterStepUtils';
import './RegisterStepCounter.css';

interface RegisterStepProps {
    step: number;
}

export const RegistrationStepCounter:React.FC<RegisterStepProps> = ({step}) => {
    return (
        <div className="reg-step-counter-container">
            <div className="reg-step-counter-btn">
                {displayIcon(step)}
            </div>
            <span className="reg-step-number">Step {step} of 6</span>
        </div>
    )
};
