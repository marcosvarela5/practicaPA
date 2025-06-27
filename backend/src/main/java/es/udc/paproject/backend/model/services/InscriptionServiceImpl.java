package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionDao inscriptionDao;

    @Autowired
    private TrialDao trialDao;

    @Autowired
    private UserDao userDao;

    // [FUNC-3]
    @Override
    public Inscription inscribeUser(Long userId, Long trialId, String cardNumber)
            throws MaxInscriptionsReachedException, DuplicateInscriptionException, DateExpiredException, InstanceNotFoundException {

        Optional<Trial> foundTrial = trialDao.findById(trialId);

        Optional<User> foundUser = userDao.findById(userId);

        if (foundTrial.isEmpty()){
            throw new InstanceNotFoundException("project.entities.trial", trialId);
        }

        if (foundUser.isEmpty()){
            throw new InstanceNotFoundException("project.entities.user", userId);
        }

        if (foundTrial.get().getMaxParticipants()==foundTrial.get().getNumParticipants()){
            throw new MaxInscriptionsReachedException();
        }

        Optional<Inscription> duplicateInstance = inscriptionDao.findByUserIdAndTrialId(userId, trialId);

        if (duplicateInstance.isPresent()){
            throw new DuplicateInscriptionException(duplicateInstance.get().getId());
        }

        if (LocalDateTime.now().isAfter(foundTrial.get().getDateTime())){
            throw new DateExpiredException();
        }

        int dorsalNumber = foundTrial.get().getNumParticipants() + 1;

        Inscription newInscription = new Inscription(foundUser.get(), foundTrial.get(), dorsalNumber, cardNumber);

        foundTrial.get().setNumParticipants(foundTrial.get().getNumParticipants() + 1); // increase num participants

        inscriptionDao.save(newInscription);

        return newInscription;
    }

    // [FUNC-5] Visualización del histórico de inscripciones.
    @Override
    public Block<Inscription> findInscriptionsByUser(Long userId, int page, int size) {
        Slice<Inscription> slice = inscriptionDao.findByUserIdOrderByDateTimeDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    // [FUNC-6]
    @Override
    public void rateTrial(Long inscriptionId, Long userId, int score) throws
            InstanceNotFoundException, AlreadyScoredException, DateExpiredException, DifferentUserException {

        Optional<Inscription> foundInscription = inscriptionDao.findById(inscriptionId);

        if (foundInscription.isEmpty()){
            throw new InstanceNotFoundException("project.entities.inscription", inscriptionId);
        }

        if (!foundInscription.get().getUser().getId().equals(userId)){
            throw new DifferentUserException(foundInscription.get().getUser().getId(), userId);
        }

        if (foundInscription.get().isScored()){
            throw new AlreadyScoredException();
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        Trial foundTrial = foundInscription.get().getTrial();

        if (!(foundTrial.getDateTime().isBefore(localDateTime) && foundTrial.getDateTime().plusDays(15).isAfter(localDateTime))) {
            throw new DateExpiredException();
        }

        foundTrial.setSumScores(foundTrial.getSumScores() + score);
        foundTrial.setNumScores(foundTrial.getNumScores() + 1);

        foundInscription.get().setScore(score);
        foundInscription.get().setScored(true);

    }

    // [FUNC-4]
    @Override
    public int giveNumber(Long inscriptionId, Long trialId, String cardNumber)
            throws InstanceNotFoundException, DateExpiredException,
            NumberAlreadyDeliveredException, InvalidCreditCardException, IncorrectTrialException {

        Optional<Inscription> foundInscription = inscriptionDao.findById(inscriptionId);

        if (foundInscription.isEmpty()){
            throw new InstanceNotFoundException("project.entities.inscription", inscriptionId);
        }

        // 1. El código de inscripción está asociado a la prueba
        if (!foundInscription.get().getTrial().getId().equals(trialId)){
            throw new IncorrectTrialException(foundInscription.get().getTrial().getId(), trialId);
        }

        // 2. La prueba deportiva no comenzó
        if (LocalDateTime.now().isAfter(foundInscription.get().getTrial().getDateTime())){
            throw new DateExpiredException();
        }

        // 3. El dorsal no fue entregado anteriormente
        if (foundInscription.get().isNumberDelivered()){
            throw new NumberAlreadyDeliveredException(foundInscription.get().getId());
        }

        // 4. La inscripción se hizo con esa tarjeta
        if (!foundInscription.get().getCardNumber().equals(cardNumber)){
            throw new InvalidCreditCardException(foundInscription.get().getId(), cardNumber);
        }

        foundInscription.get().setNumberDelivered(true);

        return foundInscription.get().getNumber();
    }
}
