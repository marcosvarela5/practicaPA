import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as RegistrationForm} from './components/RegistrationForm.jsx';
export {default as RegistrationCompleted} from './components/RegistrationCompleted.jsx';
export {default as DeliverDorsalForm} from './components/DeliverDorsalForm.jsx'
export {default as Inscriptions} from './components/Inscriptions.jsx'
export {default as FindInscriptionResult} from './components/FindInscriptionResult.jsx'
export {default as InscriptionLink} from './components/InscriptionLink.jsx'
export {default as RateTrialForm} from './components/RateTrialForm.jsx'

export default {actions, actionTypes, reducer, selectors};