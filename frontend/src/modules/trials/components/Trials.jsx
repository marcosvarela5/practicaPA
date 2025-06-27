import {FormattedMessage, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';
import { TrialLink } from '../../common';
import * as selectors from '../selectors';

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
        <td>
            {formatedDate} - {formatedTime}
        </td>
    );
}

const Trials = ({ trials, provinces, trialTypes }) => (
    <table className="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.trials.fields.name'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.trials.fields.province'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.trials.fields.trialType'/>
            </th>

            <th scope="col">
                <FormattedMessage id='project.trials.fields.dateTime'/>
            </th>

            <th scope="col">
                <FormattedMessage id='project.trials.fields.avgScore'/>
            </th>
        </tr>
        </thead>
        <tbody>
        {trials.map(trial => (
            <tr key={trial.id}>
                <td>
                    <TrialLink id={trial.id} trialName={trial.trialName} />
                </td>
                <td>{selectors.getProvinceName(provinces, trial.provinceId)}</td>
                <td>{selectors.getTrialTypeName(trialTypes, trial.trialTypeId)}</td>
                {DateTimeFormatFromEpoch(trial.dateTime)}
                <td>
                    {trial.hasScores ? (
                        <FormattedNumber value={trial.avgScore}/>
                    ) : (
                        <FormattedMessage id='project.trials.fields.noScores'/>
                    )}
                </td>
            </tr>
        ))}
        </tbody>
    </table>
);

Trials.propTypes = {
    trials: PropTypes.array.isRequired,
    provinces: PropTypes.array.isRequired,
    trialTypes: PropTypes.array.isRequired,
};

export default Trials;
