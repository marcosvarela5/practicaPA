import backend from "../../backend";
import * as actionTypes from './actionTypes';

const inscribeUserCompleted = (inscription, trialName) => ({
    type: actionTypes.INSCRIBE_USER_COMPLETED,
    inscription,
    trialName
});

const giveNumberCompleted = number => ({
    type: actionTypes.GIVE_NUMBER_COMPLETED,
    number
});

export const inscribeUser = (trialId, trialName, cardNumber, onSuccess, onErrors) => dispatch =>
    backend.inscriptionService.inscribeUser(trialId, cardNumber, inscription => {
            dispatch((inscribeUserCompleted(inscription, trialName)));
            onSuccess();
        },
        onErrors);

export const giveNumber = (trialId, inscriptionId, cardNumber, onSuccess, onErrors) => dispatch =>
    backend.inscriptionService.giveNumber(trialId, inscriptionId, cardNumber,
        number => {
            dispatch(giveNumberCompleted(number));
            onSuccess(number);
        },
        onErrors);

export const rateTrial = (inscriptionId, score, onSuccess, onErrors) => () =>
    backend.inscriptionService.rateTrial(inscriptionId, score, onSuccess, onErrors);

export const selectAnInscriptionToRate = (inscription, trialName) => ({
    type: actionTypes.INSCRIPTION_SELECTED_TO_RATE,
    inscription,
    trialName
});

const findInscriptionsByUserCompleted = inscriptionSearch => ({
    type: actionTypes.FIND_INSCRIPTIONS_BY_USER_COMPLETED,
    inscriptionSearch
});
export const clearInscriptionSearch = () => ({
    type: actionTypes.CLEAR_INSCRIPTION_SEARCH
});


export const findInscriptionsByUser = criteria => dispatch => {

    backend.inscriptionService.findInscriptionByUser(criteria,
        result => dispatch(findInscriptionsByUserCompleted({criteria, result})));
}

export const previousFindInscriptionsResultPage = criteria => dispatch => {
    dispatch(clearInscriptionSearch());
    dispatch(findInscriptionsByUser({page: criteria.page-1}));
}

export const nextFindInscriptionsResultPage = criteria => dispatch => {
    dispatch(clearInscriptionSearch());
    dispatch(findInscriptionsByUser({page: criteria.page+1}));
}