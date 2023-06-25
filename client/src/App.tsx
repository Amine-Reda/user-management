import React, {Suspense} from 'react';
import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route,
} from "react-router-dom";


const HomePage = React.lazy(() => import('./views/HomePage'));
const DashboardPage = React.lazy(() => import('./views/DashboardPage'));

function App() {
    return (
        <Router>
            <Suspense fallback={<div>Loading...</div>}>
                <Routes>
                    <Route path={'/'} Component={HomePage}/>
                    <Route path={'/dashboard'} Component={DashboardPage} />
                </Routes>
            </Suspense>
        </Router>
    );
}

export default App;
