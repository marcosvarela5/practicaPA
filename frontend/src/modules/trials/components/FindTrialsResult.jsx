import { useSelector, useDispatch } from 'react-redux';
import { FormattedMessage } from 'react-intl';

import * as selectors from '../selectors';
import * as actions from '../actions';
import { Pager } from '../../common';
import Trials from './Trials';

const FindTrialsResult = () => {
    const trialSearch = useSelector(selectors.getTrialSearch);
    const provinces = useSelector(selectors.getProvinces);
    const trialTypes = useSelector(selectors.getTrialTypes);
    const dispatch = useDispatch();

    if (!trialSearch) {
        return null;
    }

    if (trialSearch.result.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.trials.FindTrialsResult.noTrialsFound' />
            </div>
        );
    }

    return (
        <div>
            <Trials trials={trialSearch.result.items} provinces={provinces} trialTypes={trialTypes} />
            <Pager
                back={{
                    enabled: trialSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindProductsResultPage(trialSearch.criteria))
                }}
                next={{
                    enabled: trialSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindProductsResultPage(trialSearch.criteria))
                }} />
        </div>
    );
}

export default FindTrialsResult;

