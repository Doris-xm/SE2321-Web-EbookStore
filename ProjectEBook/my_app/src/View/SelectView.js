import BasicView  from "./User/BasicView";
import {getUser} from "../Service/UserService";
import {USERMODE} from "../util/Constant";
import AdminView from "./Admin/AdminView";

const SelectView = () => {
    const user = getUser();
    console.log("SelectView::",user)
    if(user == null ){
        return <BasicView/>
    }
    if(user.userMode === USERMODE.ADMIN){
        return <AdminView/>
    }
    if(user.userMode === USERMODE.USER){
        return <BasicView/>
    }
};

export default SelectView;