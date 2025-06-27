package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Inscription;
import es.udc.paproject.backend.model.exceptions.*;

import java.time.LocalDateTime;

public interface  InscriptionService {

    // [FUNC-3]
    Inscription inscribeUser (Long userId, Long trialId, String cardNumber)
            throws MaxInscriptionsReachedException, DuplicateInscriptionException, DateExpiredException, InstanceNotFoundException;

    // [FUNC-5] Visualización del histórico de inscripciones.
    Block<Inscription> findInscriptionsByUser(Long userId, int page, int size);

    void rateTrial (Long inscriptionId, Long userId, int score) throws
            InstanceNotFoundException, AlreadyScoredException, DateExpiredException, DifferentUserException;

    // [FUNC-4] Entrega de dorsal
    int giveNumber(Long inscriptionId, Long trialId, String cardNumber)
            throws InstanceNotFoundException, DateExpiredException,
            NumberAlreadyDeliveredException, InvalidCreditCardException, IncorrectTrialException;
}
