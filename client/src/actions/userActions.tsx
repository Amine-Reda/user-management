import axios from "axios";
import {GET_USERS} from "./types";


export const getUsers = () => async (dispatch:any) => {
     await axios.get("http://localhost:8080/users")
         .then((res) => {
        dispatch({ type: GET_USERS, payload: res.data });
    });
};