import React, {useState} from 'react';
import './HomePage.css'
import {
    MDBCollapse,
    MDBContainer,
    MDBIcon, MDBInput,
    MDBListGroup,
    MDBListGroupItem,
    MDBNavbar, MDBNavbarBrand, MDBNavbarItem, MDBNavbarNav, MDBNavbarToggler,
    MDBRipple
} from "mdb-react-ui-kit";

export const HomePage = () => {
    const [showShow, setShowShow] = useState(false);

    const toggleShow = () => setShowShow(!showShow);
    return (
        <>
            <MDBCollapse show={showShow} tag="nav" className="d-lg-block bg-white sidebar">
                <div className="position-sticky">
                    <MDBListGroup  className="mx-3 mt-4">
                        <MDBRipple rippleTag='span'>
                            <MDBListGroupItem tag='a' href='/' action className='border-0 border-bottom rounded'>
                                <MDBIcon fas icon="users me-3" />
                                Users
                            </MDBListGroupItem>
                        </MDBRipple>
                    </MDBListGroup>
                </div>
            </MDBCollapse>
            <MDBNavbar expand='lg' light bgColor='light'>
                <MDBContainer fluid>
                    <MDBNavbarNav className="d-flex flex-row align-items-center w-auto">
                        <MDBNavbarToggler
                            type='button'
                            aria-label='Toggle navigation'
                            onClick={toggleShow}
                        >
                            <MDBIcon icon='bars' fas />
                        </MDBNavbarToggler>
                        <MDBNavbarBrand href='#'>
                            <img
                                src='https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.webp'
                                height='30'
                                alt=''
                                loading='lazy'
                            />
                        </MDBNavbarBrand>

                        <MDBCollapse navbar>
                            <MDBNavbarItem className="d-flex align-items-center">
                                <MDBInput label='Search (ctrl + "/" to focus)' id='form1' type='text' />
                                <MDBIcon fas icon="search mx-2" />
                            </MDBNavbarItem>
                        </MDBCollapse>


                    </MDBNavbarNav>
                </MDBContainer>
            </MDBNavbar>
        </>
    );

}


export default HomePage;