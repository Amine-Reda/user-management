import {MDBBtn, MDBCol, MDBInput, MDBRow} from "mdb-react-ui-kit";
import "./CreateUser.css"
import React from "react";
import {useFormFields} from "../Form/useFormFields";
import {createUser} from "../../actions/userActions";
import {CreateUserModel} from "../../model/CreateUser.model";
import Store from "../../Store";
import {useNavigate} from "react-router-dom";


const CreateUser = () => {
    const navigate = useNavigate();
    const [inputs, handleInputChange] = useFormFields({
        firstName: "",
        lastName: "",
        email: "",
    });

    const handleSubmitItem = (event: React.FormEvent) => {
        const newUser:CreateUserModel = {
            firstName: inputs.firstName,
            lastName: inputs.lastName,
            email: inputs.email,
        }
        Store.dispatch(createUser(newUser,navigate));
        event.preventDefault();
    };

    return (
        <>
        <form className="form-width" onSubmit={handleSubmitItem}>
            <h1>Add user</h1>
            <MDBRow className='mb-4'>
                <MDBCol>
                    <MDBInput id='firstName'
                              label='First name'
                              value={inputs.firstName}
                              onChange={handleInputChange}/>
                </MDBCol>
                <MDBCol>
                    <MDBInput id='lastName'
                              label='Last name'
                              value={inputs.lastName}
                              onChange={handleInputChange}/>
                </MDBCol>
            </MDBRow>
            <MDBInput className='mb-4'
                      type='email'
                      id='email'
                      label='Email address'
                      value={inputs.email}
                      onChange={handleInputChange}/>
            <MDBBtn type='submit' className='mb-4' block>
                Submit
            </MDBBtn>

        </form>
        </>
    );
};


export default CreateUser;