import React from "react";
import ReactDOM from "react-dom/client";
import {App} from "./App";

import './assets/global.css';

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

console.log(root);

root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);