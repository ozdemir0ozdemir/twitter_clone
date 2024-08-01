import {RegisterFormOne} from "../components/RegisterFormOne/RegisterFormOne";

export const DetermineModalContent = (step: number): JSX.Element => {
    switch (step) {
        case 1:
            return <RegisterFormOne />
        case 2:
            return <span>Registration Step 2</span>
        case 3:
            return <span>Registration Step 3</span>
        case 4:
            return <span>Registration Step 4</span>
        case 5:
            return <span>Registration Step 6</span>
        case 6:
            return <span>Registration Step 6</span>
        default:
            return <></>
    }
};