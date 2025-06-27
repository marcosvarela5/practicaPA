import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import {useEffect} from "react";
import { Inscriptions } from "../index.js";


const FindInscriptionResult = () => {

    const inscriptionSearch = useSelector(selectors.getInscriptionSearch);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(actions.findInscriptionsByUser({page: 0}));
        return () => dispatch(actions.clearInscriptionSearch());
    }, [dispatch]);

    if (!inscriptionSearch) {
        return null;
    }

    if (inscriptionSearch.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.inscriptions.FindInscriptionResult.noInscriptions'/>
            </div>
        );
    }

    return (
        <div>
            {}
            <Inscriptions inscriptions={inscriptionSearch.result.items}/>
            <Pager
                back={{
                    enabled: inscriptionSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindInscriptionsResultPage(inscriptionSearch.criteria))
                }}
                next={{
                    enabled: inscriptionSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindInscriptionsResultPage(inscriptionSearch.criteria))
                }}/>
        </div>
    );
}

export default FindInscriptionResult;
