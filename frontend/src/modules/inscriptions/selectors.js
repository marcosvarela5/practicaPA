const getModuleState = state => state.inscriptions;

export const getInscription = state =>
    getModuleState(state).inscription;

export const getTrialName = state =>
    getModuleState(state).trialName;

export const getInscriptionSearch = state =>
    getModuleState(state).inscriptionSearch;

export const getNumber = state =>
    getModuleState(state).number;