import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import {useNavigate} from "react-router-dom";

const RegistrationCompleted = () => {

    const inscription = useSelector(selectors.getInscription);
    const trialName = useSelector(selectors.getTrialName);
    const navigate = useNavigate();

    if (!inscription) {
        return null;
    }

    const handleEvent = () => {
        navigate(`/`);
    }

    return (
        <div className="card card-header">
            <FormattedMessage id="project.inscriptions.RegistrationCompleted.inscriptionSuccess"/>:
            <div className="card-body">
                <div>
                    <FormattedMessage id="project.inscriptions.RegistrationCompleted.inscriptionCode"/>: {inscription.id}
                </div>
                <div>
                    <FormattedMessage id="project.inscriptions.RegistrationCompleted.inscriptionNumber"/>: {inscription.number}
                </div>
                <div>
                    <FormattedMessage id="project.inscriptions.RegistrationCompleted.inscriptionTrialName"/>: {trialName}
                </div>
            </div>
            <div className="card-footer">
                <button className="btn btn-primary my-2 my-sm-0" onClick={event => handleEvent(event)}>
                    <FormattedMessage id="project.inscriptions.RegistrationCompleted.done"></FormattedMessage>
                </button>
            </div>
        </div>
    );

}

export default RegistrationCompleted;
