import {Errors} from "../../common/index.js";
import {useState} from "react";
import {FormattedMessage} from "react-intl";
import * as actions from "../actions.js";
import {useDispatch} from "react-redux";

// eslint-disable-next-line react/prop-types
const DeliverDorsalForm = ({trialId: trialId}) => {
    const dispatch = useDispatch();
    const [cardNumber, setCardNumber] = useState('');
    const [inscriptionId, setInscriptionId] = useState('');
    const [dorsalNumber, setDorsalNumber] = useState(null);
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {
        console.log(trialId)
        setDorsalNumber(null)

        event.preventDefault();

        if (form.checkValidity()) {
            dispatch(actions.giveNumber(trialId, inscriptionId, cardNumber,
                (number) => setDorsalNumber(number),
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (
        <div>
            { dorsalNumber != null ?
                <div className="alert alert-success alert-dismissible fade show" role="alert">
                    <FormattedMessage id="project.inscriptions.DeliverDorsalForm.Success"/>
                    {dorsalNumber}
                </div>
                :
                null
            }
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.inscriptions.DeliverDorsalForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
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
                            <label htmlFor="inscriptionId" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.inscriptions.DeliverDorsalForm.inscriptionId"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="inscriptionId" className="form-control"
                                       min="0"
                                       value={inscriptionId}
                                       onChange={e => setInscriptionId(e.target.value)}
                                       required
                                />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary">
                                        <FormattedMessage id="project.inscriptions.DeliverDorsalForm.submitButton"/>
                                    </button>
                                </div>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default DeliverDorsalForm;