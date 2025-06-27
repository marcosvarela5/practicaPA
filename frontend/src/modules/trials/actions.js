import backend from "../../backend";
import * as actionTypes from './actionTypes';
import * as selectors from './selectors';


const findTrialsCompleted = trialSearch => ({
    type: actionTypes.FIND_TRIALS_COMPLETED,
    trialSearch
});

export const findTrials = criteria => dispatch => {
    //dispatch(clearProductSearch());
    backend.trialService.findTrials(criteria,
        result => dispatch(findTrialsCompleted({criteria, result})));
}

const findAllTrialTypesCompleted = trialTypes => ({
    type: actionTypes.FIND_ALL_TRIAL_TYPES_COMPLETED,
    trialTypes
});

export const findAllTrialTypes = () => (dispatch, getState) => {

    const trialTypes = selectors.getTrialTypes(getState())

    if (!trialTypes) {

        backend.trialService.findAllTrialTypes(
            trialTypes => dispatch(findAllTrialTypesCompleted(trialTypes))
        );
    }
}

const findAllProvincesCompleted = provinces => ({
    type: actionTypes.FIND_ALL_PROVINCES_COMPLETED,
    provinces
});

export const findAllProvinces = () => (dispatch, getState) => {

    const provinces = selectors.getProvinces(getState())

    if (!provinces) {

        backend.trialService.findAllProvinces(
            provinces => dispatch(findAllProvincesCompleted(provinces))
        );
    }
}

export const previousFindProductsResultPage = criteria =>
    findTrials({...criteria, page: criteria.page-1});

export const nextFindProductsResultPage = criteria =>
    findTrials({...criteria, page: criteria.page+1});

const findTrialByIdCompleted = trial => ({
    type: actionTypes.FIND_TRIAL_BY_ID_COMPLETED,
    trial
});

export const findTrialById = id => dispatch => {
    backend.trialService.findByTrialId(id,
        trial => dispatch(findTrialByIdCompleted(trial)));
}

export const clearTrial = () => ({
    type: actionTypes.CLEAR_TRIAL
});

