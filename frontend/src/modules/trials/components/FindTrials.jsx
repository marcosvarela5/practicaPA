import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import TrialTypeSelector from './TrialTypeSelector';
import * as actions from '../actions';
import ProvinceSelector from "./ProvinceSelector.jsx";

const FindTrials = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [trialTypeId, setTrialTypeId] = useState('');
    const [provinceId, setProvinceId] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    const handleSubmit = event => {
        event.preventDefault();
        dispatch(actions.findTrials(
            {
                trialTypeId: toNumber(trialTypeId),
                provinceId: toNumber(provinceId),
                startDate,
                endDate,
                page: 0
            }));
        navigate('/trials/find-trials-result');
    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return (

        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <TrialTypeSelector id="trialTypeId" className="custom-select my-1 mr-sm-2"
                              value={trialTypeId} onChange={e => setTrialTypeId(e.target.value)}/>

            <ProvinceSelector id="provinceId" className="custom-select my-1 mr-sm-2"
                               value={provinceId} onChange={e => setProvinceId(e.target.value)}/>

            <FormattedMessage id="project.trials.trialSearch.startDate"/>
            <input id="startDate" type="date" className="form-control" value={startDate}
                   onChange={e => setStartDate(e.target.value)}/>

            <FormattedMessage id="project.trials.trialSearch.endDate"/>
            <input id="endDate" type="date" className="form-control" value={endDate}
                   onChange={e => setEndDate(e.target.value)}/>

            <button type="submit" id="submitTrialSearchButton" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search'/>
            </button>
        </form>

    );
}

export default FindTrials;