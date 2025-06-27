import {config, appFetch} from './appFetch';


export const findAllProvinces = (onSuccess) =>
    appFetch('/contest/provinces', config('GET'), onSuccess);

export const findAllTrialTypes = (onSuccess) =>
    appFetch('/contest/trialTypes', config('GET'), onSuccess);

export const findTrials = ({trialTypeId, provinceId, startDate, endDate, page},
                             onSuccess) => {

    let path = `/trialSearch/trials?page=${page}`;

    path += trialTypeId ? `&trialTypeId=${trialTypeId}` : "";
    path += provinceId ? `&provinceId=${provinceId}` : "";
    path += startDate ? `&startDate=${startDate}` : "";
    path += endDate ? `&endDate=${endDate}` : "";

    appFetch(path, config('GET'), onSuccess);

}

export const findByTrialId = (id, onSuccess) =>
    appFetch(`/trialSearch/trials/${id}`, config('GET'), onSuccess);