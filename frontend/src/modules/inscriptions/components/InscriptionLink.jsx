import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';
import {FormattedMessage} from "react-intl";


const InscriptionLink = ({inscription}) => {

    if (inscription && inscription.trial && inscription.trial.id) {
        const trialId = inscription.trial.id;
        return (
            <Link to={`/trials/${trialId}`}>
                {trialId}
            </Link>
        );
    } else {
        return <span>No hay información de trial disponible</span>;
    }

}

InscriptionLink.propTypes = {
    inscription: PropTypes.shape({
        trial: PropTypes.shape({
            id: PropTypes.number.isRequired
        })
    }).isRequired
};

export default InscriptionLink;