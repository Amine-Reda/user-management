import User from "../../interfaces/user.interface";
import React from "react";
import {
    MDBBtn,
    MDBDropdown, MDBDropdownItem,
    MDBDropdownMenu,
    MDBDropdownToggle,
    MDBTable,
    MDBTableBody,
    MDBTableHead
} from "mdb-react-ui-kit";
import Avatar, { genConfig } from 'react-nice-avatar'
import store from "../../Store";
import {deleteUser} from "../../actions/userActions";
import UpdateUser from "../UpdateUser/UpdateUser";
import {UPDATE_USER} from "../../actions/types";
const config = genConfig()

const UserTable = ({users}: any) => {

    const handleDeleteUser=(user:User)=>{
        if(window.confirm("Are you use, you wan to delete this user")) {
            store.dispatch(deleteUser(user.id))
        }
    }


    return (
        <div style={{width:"inherit"}}>
            <MDBTable align='middle'>
                <MDBTableHead>
                    <tr>
                        <th scope='col'>Name</th>
                        <th scope='col'>firstName</th>
                        <th scope='col'>LastName</th>
                        <th scope='col'>Email</th>
                        <th scope='col'>Actions</th>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    {users.map((user:User,key:string)=>(
                        <tr key={key}>
                        <td>
                            <div className='d-flex align-items-center'>
                                <Avatar style={{ width: '4rem', height: '4rem' }} {...config} />
                            </div>
                        </td>
                        <td>
                            <p className='fw-normal mb-1'>{user.firstName}</p>
                        </td>
                        <td>
                            <p className='fw-normal mb-1'>{user.lastName}</p>
                        </td>

                        <td><p className='fw-normal mb-1'>{user.email}</p></td>
                        <td>
                            <MDBDropdown>
                            <MDBDropdownToggle color='primary'>Action</MDBDropdownToggle>
                            <MDBDropdownMenu>
                                <MDBDropdownItem link href={`/updateUser/${user.id}`}>Update</MDBDropdownItem>
                                <MDBDropdownItem link  onClick={()=>handleDeleteUser(user)}>Delete</MDBDropdownItem>
                            </MDBDropdownMenu>
                        </MDBDropdown>
                        </td>
                    </tr>
                    ))  }
                </MDBTableBody>
            </MDBTable>
        </div>
    );
};

export default UserTable;