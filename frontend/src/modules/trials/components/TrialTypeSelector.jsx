import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';

const TrialTypeSelector = (selectProps) => {
    const trialTypes = useSelector(selectors.getTrialTypes);

    return (

        <select {...selectProps}>

            <FormattedMessage id='project.trials.TrialTypeSelector.allTrialTypes'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {trialTypes && trialTypes.map(trialType =>
                <option key={trialType.id} value={trialType.id}>{trialType.trialTypeName}</option>
            )}

        </select>

    );
}

TrialTypeSelector.propTypes = {
    selectProps: PropTypes.object
};

export default TrialTypeSelector;