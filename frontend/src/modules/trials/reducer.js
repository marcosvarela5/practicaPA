import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    trialSearch: null,
    trial: null,
    trialTypes: null,
    provinces: null
};

const trialSearch = (state = initialState.trialSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_TRIALS_COMPLETED:
            return action.trialSearch;

        default:
            return state;

    }

}

const trial = (state = initialState.trial, action) => {

    switch (action.type) {

        case actionTypes.FIND_TRIAL_BY_ID_COMPLETED:
            return action.trial;

        case actionTypes.CLEAR_TRIAL:
            return initialState.trial;

        default:
            return state;
    }
}

const trialTypes = (state = initialState.trialTypes, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_TRIAL_TYPES_COMPLETED:
            return action.trialTypes;
        default:
            return state;

    }
}

const provinces = (state = initialState.provinces, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_PROVINCES_COMPLETED:
            return action.provinces;
        default:
            return state;
    }
}

const reducer = combineReducers({
    trialSearch,
    trial,
    trialTypes,
    provinces
});

export default reducer;