import {init} from './appFetch';
import * as userService from './userService';
import * as trialService from './trialService.js';
import * as inscriptionService from './inscriptionService.js';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, trialService, inscriptionService};
