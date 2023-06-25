import {applyMiddleware, compose, legacy_createStore} from 'redux';

import rootReducer from "./reducers/rootReducer";
import thunk from "redux-thunk";

declare global {
    interface Window {
        __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose;
    }
}
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;


const store = legacy_createStore(rootReducer, composeEnhancers(applyMiddleware(thunk)))

export default store;