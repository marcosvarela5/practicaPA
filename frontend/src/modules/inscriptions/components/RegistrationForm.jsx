import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';

// eslint-disable-next-line react/prop-types
const RegistrationForm = ({trialId: trialId, trialName: trialName}) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [cardNumber, setCardNumber] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {
        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.inscribeUser(trialId, trialName, {cardNumber: cardNumber},
                () => navigate('/inscriptions/registration-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (

        <div>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.inscriptions.RegistrationForm.title"/>
                </h5>
                <div className="card-body">
                    <form id="RegistrationForm" ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="cardNumber" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.inscriptions.RegistrationForm.cardNumber"/>
                            </label>
                            <div className="col-md-4">
                                <input type="tel" id="cardNumber" className="form-control"
                                       pattern="[0-9]{16,16}"
                                       maxLength="16"
                                       value={cardNumber}
                                       onChange={e => setCardNumber(e.target.value)}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.inscriptions.RegistrationForm.inscription"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}



export default RegistrationForm;