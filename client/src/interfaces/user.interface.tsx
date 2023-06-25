import Right from "./right.interface";

interface User {
    id:number
    firstName: string;
    lastName:string
    email: string;
    right:Right
}
export default User;