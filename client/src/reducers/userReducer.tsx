import {GET_USERS, CREATE_USER, DELETE_USER, UPDATE_USER, GET_USER, SET_USERS} from '../actions/types'
import User from "../interfaces/user.interface";

interface UserState {
    users: User[];
    currentUser: User | null;
    loading: boolean;
    error: string | null;
}

const initialState: UserState = {
    users: [],
    currentUser: null,
    loading: false,
    error: null,
};


export default function (state = initialState, action:any) {
    switch (action.type) {
        case GET_USERS:
            return {...state,users:action.payload,loading:true,error:null}
        case GET_USER:
            return {...state,loading:true,error: null}
        case DELETE_USER:
            return
        case CREATE_USER:
            return
        case UPDATE_USER:
            return
        case SET_USERS:
            return {
                ...state,
                users: action.payload,
                loading: false,
                error: null,
            };
        default:
            return state;
    }
}