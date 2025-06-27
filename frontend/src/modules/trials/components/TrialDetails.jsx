import {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';
import {DeliverDorsalForm, RegistrationForm} from "../../inscriptions/index.js";

const DateTimeFormatFromEpoch = (timestamp) => {
    const date = new Date(timestamp * 1000);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Agregar cero adelante si es necesario
    const day = String(date.getDate()).padStart(2, '0');
    const hour = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    const formatedDate = `${day}/${month}/${year}`;
    const formatedTime = `${hour}:${minutes}:${seconds}`;

    return (
        <p className="card-text font-weight-bold" id="trialDate">
            <FormattedMessage id='project.trials.fields.dateTime'/>{': '}
            {formatedDate} - {formatedTime}
        </p>
    );
}

const TrialDetails = () => {
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    let user = useSelector(users.selectors.getUser);

    const trial = useSelector(selectors.getTrial);
    const dispatch = useDispatch();
    const {id} = useParams();
    const trialTypes = useSelector(selectors.getTrialTypes);
    const provinces = useSelector(selectors.getProvinces);

    useEffect(() => {

        const trialId = Number(id);

        if (!Number.isNaN(trialId)) {
            dispatch(actions.findTrialById(trialId));
        }

        return () => dispatch(actions.clearTrial());

    }, [id, dispatch]);

    if (!trial) {
        return null;
    }

    const trialTypeName = selectors.getTrialTypeName(trialTypes, trial.trialTypeId);
    const provinceName = selectors.getProvinceName(provinces, trial.provinceId);

    return (

        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">{trial.trialName}</h5>
                    <p className="card-text" id="trialDescription">{trial.trialDescription}</p>
                    <p className="card-text font-weight-bold" id="inscriptionPrice">
                        <FormattedMessage id='project.global.fields.price'/>{': '}
                        <FormattedNumber value={trial.inscriptionPrice} style="currency" currency="EUR"/>
                    </p>
                    <p className="card-text font-weight-bold" id="avgScore">
                        <FormattedMessage id='project.trials.fields.avgScore'/>{': '}
                        {trial.hasScores ? (
                            <FormattedNumber value={trial.avgScore}/>
                        ) : (
                            <FormattedMessage id='project.trials.fields.noScores'/>
                        )}
                    </p>
                    <p className="card-text font-weight-bold" id="trialPlace">
                        <FormattedMessage id='project.trials.fields.place'/>{': '}
                        {trial.place}
                    </p>

                    <p className="card-text font-weight-bold" id="trialTypeName">
                        <FormattedMessage id='project.trials.fields.trialType'/>{': '}
                        {trialTypeName}
                    </p>
                    <p className="card-text font-weight-bold" id="provinceName">
                        <FormattedMessage id='project.trials.fields.province'/>{': '}
                        {provinceName}
                    </p>

                    {DateTimeFormatFromEpoch(trial.dateTime)}

                    <p className="card-text font-weight-bold" id="maxParticipants">
                        <FormattedMessage id='project.trials.fields.maxParticipants'/>{': '}
                        {trial.maxParticipants}
                    </p>
                    <p className="card-text font-weight-bold" id="numParticipants">
                        <FormattedMessage id='project.trials.fields.numParticipants'/>{': '}
                        {trial.numParticipants}
                    </p>
                </div>
            </div>

            {loggedIn && user.role === "PARTICIPANT" && trial.showRegister &&
                <div>
                    <br/>
                    <RegistrationForm trialId={Number(id)} trialName={trial.trialName}></RegistrationForm>
                </div>
            }


            {loggedIn && user.role === "EMPLOYEE" && trial.showGiveNumber &&
                <div>
                    <br/>
                    <DeliverDorsalForm trialId={Number(id)}/>
                </div>
            }

        </div>

    );

}

export default TrialDetails;