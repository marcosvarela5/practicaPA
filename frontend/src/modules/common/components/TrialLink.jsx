import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

const TrialLink = ({id, trialName}) => {
    return (
        <Link to={`/trialSearch/trial-details/${id}`}>
            {trialName}
        </Link>
    );
}

TrialLink.propTypes = {
    id: PropTypes.number.isRequired,
    trialName: PropTypes.string.isRequired,
};

export default TrialLink;