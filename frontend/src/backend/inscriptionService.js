import {config, appFetch} from './appFetch';

export const inscribeUser = (trialId, cardNumber, onSuccess, onErrors) =>
    appFetch(`/inscriptions/${trialId}/inscribe`, config('POST', cardNumber), onSuccess, onErrors);

export const giveNumber = (trialId, inscriptionId, cardNumber, onSuccess, onErrors) =>
    appFetch(`/inscriptions/${trialId}/giveNumber`,
        config('POST', {inscriptionId, cardNumber}), onSuccess, onErrors);

export const findInscriptionByUser = ({page}, onSuccess) =>
    appFetch(`/inscriptions/inscriptionHistory?page=${page}` ,
        config('GET'), onSuccess);

export const rateTrial = (inscriptionId, score, onSuccess, onErrors) =>
    appFetch(`/inscriptions/${inscriptionId}/rateTrial`,
        config('POST', score), onSuccess, onErrors);