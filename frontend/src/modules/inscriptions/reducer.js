import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    inscription: null,
    trialName: null,
    inscriptionSearch: null,
    number: null
};

const inscription = (state = initialState.inscription, action) => {
    switch (action.type) {
        case actionTypes.INSCRIBE_USER_COMPLETED:
            return action.inscription;
        case actionTypes.INSCRIPTION_SELECTED_TO_RATE:
            return action.inscription;
        default:
            return state;
    }

}

const number = (state = initialState.number, action) => {
    switch (action.type) {
        case actionTypes.GIVE_NUMBER_COMPLETED:
            return action.number;
        default:
            return state;
    }
}

const trialName = (state = initialState.trialName, action) => {
    switch (action.type) {
        case actionTypes.INSCRIBE_USER_COMPLETED:
            return action.trialName;
        case actionTypes.INSCRIPTION_SELECTED_TO_RATE:
            return action.trialName;
        default:
            return state;
    }

}


const inscriptionSearch = (state = initialState.inscriptionSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_INSCRIPTIONS_BY_USER_COMPLETED:
            return action.inscriptionSearch;

        case actionTypes.CLEAR_INSCRIPTION_SEARCH:
            return initialState.inscriptionSearch;

        default:
            return state;
    }

}

const reducer = combineReducers({
    inscription,
    trialName,
    inscriptionSearch,
    number
});

export default reducer;