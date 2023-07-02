import {MDBBtn, MDBCol, MDBInput, MDBRow} from "mdb-react-ui-kit";
import "./UpdateUser.css"
import React, {useEffect, useState} from "react";
import {useFormFields} from "../Form/useFormFields";
import Store from "../../Store";
import {useNavigate, useParams} from "react-router-dom";
import {getUser, updateUser} from "../../actions/userActions";
import {UpdateUserModel} from "../../model/UpdateUser.model";
import store from "../../Store";
import {connect, useSelector} from "react-redux";
import User from "../../interfaces/user.interface";
import {handleInputChange} from "react-select/dist/declarations/src/utils";


const UpdateUser = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const userToUpdate:User = useSelector((state: any) => state.user.userToUpdate);
    const [inputs, setInputs] = useState<any>({
        firstName: userToUpdate.firstName,
        lastName: userToUpdate.lastName,
        email: userToUpdate.email,
    });
    
    const handleSubmitItem = (event: React.FormEvent) => {
        const updatedUser:UpdateUserModel = {
            id:userToUpdate.id,
            firstName: inputs.firstName,
            lastName: inputs.lastName,
            email: inputs.email,
        }

        Store.dispatch(updateUser(updatedUser,navigate));
        event.preventDefault();
    };

    const handleChange = (event:any) => {
        const { name, value } = event.target;
        setInputs((prevState:any) => {
            return {
                ...prevState,
                [name]: value,
            };
        });
    };

    useEffect(() => {
        store.dispatch(getUser(id))
    }, []);

    useEffect(() => {
        if(userToUpdate) {
            setInputs(userToUpdate)
        }
    }, [userToUpdate]);

    return (
        <>
            <form className="form-width" onSubmit={handleSubmitItem}>
                <h1>Update user</h1>
                <MDBRow className='mb-4'>
                    <MDBCol>
                        <MDBInput id='firstName'
                                  label='First name'
                                  name="firstName"
                                  value={inputs.firstName}
                                  onChange={handleChange}

                        />
                    </MDBCol>
                    <MDBCol>
                        <MDBInput id='lastName'
                                  label='Last name'
                                  name="lastName"
                                  value={inputs.lastName}
                                  onChange={handleChange}
                                  />
                    </MDBCol>
                </MDBRow>
                <MDBInput className='mb-4'
                          type='email'
                          id='email'
                          name="email"
                          label='Email address'
                          value={inputs.email}
                          onChange={handleChange}
                       />

                <MDBBtn type='submit' className='mb-4' block>
                    Submit
                </MDBBtn>

            </form>
        </>
    );
};

const mapStateToProps = (state:any) => ({
    userToUpdate:state.user.userToUpdate
})
export default connect(mapStateToProps,{getUser})(UpdateUser)