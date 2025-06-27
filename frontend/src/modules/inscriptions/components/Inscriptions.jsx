import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import * as actions from '../actions';
import { TrialLink } from '../../common';


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
        <p className="card-text">
            {formatedDate}
            <br/>
            {formatedTime}
        </p>
    );
}

const Inscriptions = ({ inscriptions }) => {

    const navigate = useNavigate();
    const dispacth = useDispatch();

    const handleEventRate = inscription => {

        dispacth(actions.selectAnInscriptionToRate(inscription, inscription.trialName));

        navigate(`/inscriptions/${inscription.id}/rate-trial-form`)
    }

    return(
        <table className="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.id'/>
                </th>

                <th scope="col">
                    <FormattedMessage id='project.global.fields.date'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.trialName'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.number'/>
                </th>

                <th scope="col">
                    <FormattedMessage id='project.global.fields.cardNumber'/>
                </th>

                <th scope="col">
                    <FormattedMessage id='project.global.fields.collected'/>
                </th>

                <th scope="col">
                    <FormattedMessage id='project.global.fields.score'/>
                </th>
            </tr>
            </thead>
            <tbody>
            {inscriptions.map(inscription =>
                <tr key={inscription.id}>
                    <td>{inscription.id ? inscription.id : '-'}</td>
                    <td>{DateTimeFormatFromEpoch(inscription.dateTime)}</td>
                    <td>
                        {inscription.trialId ?
                            <TrialLink id={inscription.trialId} trialName={inscription.trialName}/>
                            :
                            '-'}
                    </td>
                    <td>{inscription.number ? inscription.number : '-'}</td>
                    <td>{inscription.cardNumber ? inscription.cardNumber : '-'}</td>
                    <td>{inscription.numberDelivered ? 'Sí' : 'No'}</td>
                    <td>{inscription.scored ? inscription.score : 'No puntuada'}</td>
                    <td>
                        {inscription.showRateTrial ?
                            <button onClick={() =>
                                handleEventRate(inscription)} className="btn btn-primary my-2 my-sm-0">
                                <FormattedMessage id='project.rating.fields.rate'/>
                            </button>
                            :
                            ''
                        }
                    </td>

                </tr>
            )}
            </tbody>
        </table>
    );
}

Inscriptions.propTypes = {
    inscriptions: PropTypes.array.isRequired
};

export default Inscriptions;