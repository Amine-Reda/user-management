import React, {Suspense} from 'react';
import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route,
} from "react-router-dom";


const HomePage = React.lazy(() => import('./views/HomePage/HomePage'));
const UsersPage = React.lazy(() => import('./views/UsersPage/UsersPage'));
const CreateUser = React.lazy(() => import('./components/CreateUser/CreateUser'));
const UpdateUser = React.lazy(() => import('./components/UpdateUser/UpdateUser'));
const NotFoundPage = React.lazy(() => import('./views/NotFoundPage/NotFoundPage'));

function App() {
    return (
        <Router>
            <Suspense fallback={<div>Loading...</div>}>
                <HomePage/>
                <div className="container custom-container " >
                <Routes>
                        <Route path='/'  Component={UsersPage}  />
                        <Route path='/createUser' Component={CreateUser}/>
                        <Route path='/updateUser/:id' Component={UpdateUser}/>
                        <Route path='*' Component={NotFoundPage} />

                </Routes>
                </div>
            </Suspense>
        </Router>
    );
}

export default App;
