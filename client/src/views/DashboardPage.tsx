import {getUsers} from "../actions/userActions";
import {useEffect} from "react";
import {connect, useDispatch, useSelector} from "react-redux";
import User from "../interfaces/user.interface";
import store from "../Store";

const DashboardPage = () => {
    const dispatch = useDispatch();
    const users = useSelector((state: any) => state.user.users);
    const loading = useSelector((state: any) => state.user.loading);
    const error = useSelector((state: any) => state.user.error);



    useEffect(() => {
        store.dispatch(getUsers());
    }, []);

    const handleUserClick = (user: any) => {
        console.log(user);
        console.log(dispatch);
        console.log(loading);
        console.log(error);
    };
    return (
        <div>
            <h2>User List</h2>
            {users.map((user: User,key:string) => (
                <div key={key} onClick={() => handleUserClick(user)}>
                    <p>FullName: {user.firstName}+{user.lastName}</p>
                    <p>Email: {user.email}</p>
                </div>
            ))}
        </div>
    );
};

const mapStateToProps = (state:any) => ({
    users:state.user.users
})
export default connect(mapStateToProps,{getUsers})(DashboardPage)
