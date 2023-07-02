import React, {useEffect} from "react";
import {connect, useSelector} from "react-redux";
import {getUsers} from "../../actions/userActions";
import store from "../../Store";
import UserTable from "../../components/UserTable/UserTable";
import {MDBBtn} from "mdb-react-ui-kit";



const UsersPage = () => {
    const users = useSelector((state: any) => state.user.users);



    useEffect(() => {
        store.dispatch(getUsers());
    }, []);

    return (
        <>
            <div style={{width:'100%'}}>
                <MDBBtn  href='/createUser'>Add user</MDBBtn>
            </div>

           <UserTable users={users} />
        </>
    );
};

const mapStateToProps = (state:any) => ({
    users:state.user.users
})
export default connect(mapStateToProps,{getUsers})(UsersPage)
