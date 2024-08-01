import React from "react";

import './Landing.css';
import '../assets/global.css';
import {RegisterModal} from "../features/register/components/RegisterModal/RegisterModal";

export const Landing:React.FC = () => {
    return (
        <div className="landing-container bg-color">
            <RegisterModal />
        </div>
    )
}
