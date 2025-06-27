import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import trials from '../modules/trials'
import inscriptions from "../modules/inscriptions/index.js";

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    trials: trials.reducer,
    inscriptions: inscriptions.reducer
});

export default rootReducer;
