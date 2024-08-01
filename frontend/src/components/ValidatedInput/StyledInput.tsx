import React from 'react';
import styled from 'styled-components';
import {StyledInputProps} from '../../utils/GlobalInterfaces';
import {determineStyledInputBorder} from "../../utils/DetermineStylesUtil";


export const StyledInputBox = styled.div<StyledInputProps>`
    position: relative;
    border-radius: 5px;
    width: 100%;
    height: 56px;
    border: ${(props => determineStyledInputBorder(props))}
`;
