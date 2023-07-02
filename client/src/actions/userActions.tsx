import axios from "axios";
import {DELETE_USER, GET_ERRORS, GET_USER, GET_USERS, UPDATE_USER} from "./types";
import {CreateUserModel} from "../model/CreateUser.model";
import {Dispatch} from "redux";
import User from "../interfaces/user.interface";
const  API_URL="http://localhost:8080"
export const getUsers = () => async (dispatch:any) => {
     await axios.get(API_URL+"/users")
         .then((res) => {
        dispatch({ type: GET_USERS, payload: res.data });
    });
};

export const getUser = (userId:any) => async (dispatch:any) => {
    await axios.get(API_URL+"/users/"+`${userId}`)
        .then((res) => {
            dispatch({ type: GET_USER, payload: res.data });
        });
};

export const createUser = (newUser:CreateUserModel, navigate:any) => async (dispatch:Dispatch) => {
    await axios
        .post(API_URL+"/users", newUser)
        .then((res) => {
            navigate("/");
        })
        .catch((err) => {
            console.log(err)
            dispatch({ type: GET_ERRORS, payload: err });
        });
};

export const deleteUser = (userId:number) => async (dispatch:any) => {
    await axios.delete(API_URL+`/users/${userId}`).then((res) => {
        dispatch({ type: DELETE_USER, payload: userId });
    });
};

export const updateUser = (user:User,navigate:any) => async (dispatch:any) =>{
    await axios.put(API_URL+`/users/${user.id}`,user).then((res) => {
        navigate("/");
        dispatch({ type: UPDATE_USER, payload: res });
    }).catch((err) => {
            console.log(err)
            dispatch({ type: GET_ERRORS, payload: err });
        });;
}