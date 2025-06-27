import {FormattedMessage} from 'react-intl';
//import {Route, Routes} from 'react-router-dom';
import {FindTrials, FindTrialsResult} from "../../trials";
import {Route, Routes} from "react-router-dom";

const Home = () => (
    <div className="text-center">
        <FormattedMessage id="project.app.Home.welcome"/>
        <FindTrials/>
        <Routes>
            <Route path="/trials/find-trials-result" element={<FindTrialsResult/>}/>
        </Routes>


    </div>
);

export default Home;
