import {useSelector} from 'react-redux';
import {Route, Routes} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import {TrialDetails} from '../../trials';
import {RegistrationCompleted, FindInscriptionResult, RateTrialForm} from "../../inscriptions";

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    let user = useSelector(users.selectors.getUser);

   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Routes>
                <Route path="/*" element={<Home/>}/>
                <Route path="/trialSearch/trial-details/:id" element={<TrialDetails/>}/>
                {loggedIn && <Route path="/inscriptions/registration-completed" element={<RegistrationCompleted/>}/>}
                {loggedIn && <Route path="/inscriptions/:inscriptionId/rate-trial-form" element={<RateTrialForm/>}/>}
                {loggedIn && <Route path="/users/update-profile" element={<UpdateProfile/>}/>}
                {loggedIn && <Route path="/users/change-password" element={<ChangePassword/>}/>}
                {loggedIn && <Route path="/users/logout" element={<Logout/>}/>}
                {loggedIn && user.role === "PARTICIPANT" && <Route path="/inscriptions/inscription-history" element={<FindInscriptionResult/>}/>}
                {!loggedIn && <Route path="/users/login" element={<Login/>}/>}
                {!loggedIn && <Route path="/users/signup" element={<SignUp/>}/>}
            </Routes>
        </div>

    );

};

export default Body;
