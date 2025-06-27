const getModuleState = state => state.trials;

export const getTrialTypes = state =>
    getModuleState(state).trialTypes;

export const getProvinces = state =>
    getModuleState(state).provinces;

export const getTrialSearch = state =>
    getModuleState(state).trialSearch;

export const getTrial = state =>
    getModuleState(state).trial;

export const getTrialTypeName = (trialTypes, id) => {

    if (!trialTypes) {
        return '';
    }

    const trialType = trialTypes.find(trialType => trialType.id === id);

    if (!trialType) {
        return '';
    }

    return trialType.trialTypeName;

}

export const getProvinceName = (provinces, id) => {

    if (!provinces) {
        return 'Error: No hay provincias';
    }

    const province = provinces.find(province => province.id === id);

    if (!province) {
        return 'Error: Esta provincia no existe';
    }

    return province.provinceName;

}