import {GET_USERS, DELETE_USER, GET_USER,} from '../actions/types'
import User from "../interfaces/user.interface";


interface UserState {
    users: User[];
    userToUpdate:User;
}

const initialState: UserState = {
    users: [],
    userToUpdate:{id:0,firstName:'',lastName:'',email:''}
};


export default function (state = initialState, action:any) {
    switch (action.type) {
        case GET_USERS:
            return {...state,users:action.payload,}
        case GET_USER:
            return {...state,userToUpdate:action.payload,}
        case DELETE_USER:
            return {...state,users:state.users.filter(x => x.id !== action.payload)}
        default:
            return state;
    }
}