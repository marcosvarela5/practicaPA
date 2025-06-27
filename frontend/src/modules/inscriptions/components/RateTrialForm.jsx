import {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useParams} from 'react-router-dom';

import {BackLink, Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from "../selectors.js";

const RateTrialForm = () => {
    const dispatch = useDispatch();
    const {inscriptionId} = useParams();
    const [score, setScore] = useState('1');
    const [backendErrors, setBackendErrors] = useState(null);
    const [backendSuccess, setBackendSuccess] = useState(false);
    const trialName = useSelector(selectors.getTrialName);
    let form;

    const handleSubmit = event => {
        event.preventDefault();
        const inscriptionIdNumber = Number(inscriptionId);

        if (form.checkValidity()) {

            dispatch(actions.rateTrial(inscriptionIdNumber, {score: Number(score)},
                () => setBackendSuccess(true),
                errors => setBackendErrors(errors), setBackendSuccess(false)));

        } else {
            setBackendSuccess(false);
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (

        <div>
            <BackLink/>
            <div>
                <Errors errors={backendErrors}
                        onClose={() => setBackendErrors(null)}/>

                {backendSuccess ?
                    <div className="alert alert-success alert-dismissible fade show" role="alert">
                        <FormattedMessage id="project.inscriptions.RateTrialForm.success"/>
                    </div>
                    :
                    null
                }
                <div className="card bg-light border-dark">
                    <h5 className="card-header">
                        {trialName}
                    </h5>
                    <div className="card-body">
                        <form ref={node => form = node}
                              className="needs-validation" noValidate
                              onSubmit={(e) => handleSubmit(e)}>
                            <div className="form-group row">
                                <label htmlFor="score" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.inscriptions.RateTrialForm.score"/>
                                </label>
                                <div className="col-md-4">
                                    <select
                                        value={score}
                                        onChange={e => setScore(e.target.value)}
                                    >
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary">
                                        <FormattedMessage id="project.inscriptions.RateTrialForm.rate"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}



export default RateTrialForm;