import {useSelector} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import users from '../../users';

const Header = () => {

    const userName = useSelector(users.selectors.getUserName);
    let user = useSelector(users.selectors.getUser);

    return (

        <nav className="navbar navbar-expand-lg navbar-light bg-light border">
            <Link className="navbar-brand" to="/">PA Project</Link>
            <button className="navbar-toggler" type="button" 
                data-toggle="collapse" data-target="#navbarSupportedContent" 
                aria-controls="navbarSupportedContent" aria-expanded="false" 
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">

                <ul className="navbar-nav mr-auto">
                </ul>
                
                {userName ? 

                <ul className="navbar-nav">
                
                    <li className="nav-item dropdown">

                        <a className="dropdown-toggle nav-link" id="userNameLink" href="/"
                            data-toggle="dropdown">
                            <span className="fa-solid fa-user"></span>&nbsp;
                            {userName}
                        </a>
                        <div className="dropdown-menu dropdown-menu-right">
                            <Link className="dropdown-item" to="/users/update-profile">
                                <FormattedMessage id="project.users.UpdateProfile.title"/>
                            </Link>
                            <Link className="dropdown-item" to="/users/change-password">
                                <FormattedMessage id="project.users.ChangePassword.title"/>
                            </Link>
                            {user.role === 'PARTICIPANT' && (
                                <Link className="dropdown-item" to="/inscriptions/inscription-history">
                                    <FormattedMessage id="project.inscriptions.FindInscriptionsByUser.title"/>
                                </Link>
                            )}
                            <div className="dropdown-divider"></div>
                            <Link className="dropdown-item" to="/users/logout">
                                <FormattedMessage id="project.app.Header.logout"/>
                            </Link>
                        </div>

                    </li>

                </ul>
                
                :

                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" id="loginLink" to="/users/login">
                            <FormattedMessage id="project.users.Login.title"/>
                        </Link>
                    </li>
                </ul>
                
                }

            </div>
        </nav>

    );

};

export default Header;
