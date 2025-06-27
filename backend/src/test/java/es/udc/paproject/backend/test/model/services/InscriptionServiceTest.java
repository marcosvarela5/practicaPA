package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class InscriptionServiceTest {
    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private InscriptionDao inscriptionDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private TrialTypeDao trialTypeDao;

    @Autowired
    private TrialDao trialDao;

    private User signUpUser(String userName) {

        User user = new User(userName, "password", "firstName",
                "lastName", userName + "@" + userName + ".com");

        try {
            userService.signUp(user);
        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }

        return user;

    }

    private Trial createTrialWithNameTypeProvince(String name, TrialType type, Province province){
        return new Trial(name, "trialDescription",
                LocalDateTime.of(2025, 4, 5, 15, 39, 46),
                BigDecimal.valueOf(10), 100, "place", type, province, 0, 0);
    }

    private Trial createExpiredTrialWithNameTypeProvince(String name, TrialType type, Province province){
        return new Trial(name, "trialDescription",
                LocalDateTime.of(2020, 4, 5, 15, 39, 46),
                BigDecimal.valueOf(10), 100, "place", type, province, 0, 0);
    }

    private Trial createSmallTrialWithNameTypeProvince(String name, TrialType type, Province province){
        return new Trial(name, "trialDescription",
                LocalDateTime.of(2025, 4, 5, 15, 39, 46),
                BigDecimal.valueOf(10), 1, "place", type, province, 0, 0);
    }

    private Inscription createInscription(User user, Trial trial){
        return new Inscription(user, trial, 7, "1111222233334444");
    }

    @Test
    public void testNewInscription() throws DateExpiredException, DuplicateInscriptionException,
            MaxInscriptionsReachedException, InstanceNotFoundException {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        Inscription inscription = inscriptionService.inscribeUser(user.getId(), trial.getId(), "1234");

        Optional<Inscription> i1 = inscriptionDao.findById(inscription.getId());

        Inscription foundInscription = null;
        if (i1.isPresent()) {
            foundInscription = i1.get();
        }

        assertEquals(inscription, foundInscription);
    }

    @Test
    public void testNewInscriptionMaxInscriptionReached() throws DateExpiredException, DuplicateInscriptionException,
            MaxInscriptionsReachedException, InstanceNotFoundException {
        User user = signUpUser("user");
        User user2 = signUpUser("user2");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createSmallTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        inscriptionService.inscribeUser(user.getId(), trial.getId(), "1234");

        assertThrows(MaxInscriptionsReachedException.class, () ->
                inscriptionService.inscribeUser(user2.getId(), trial.getId(), "1234"));
    }

    @Test
    public void testNewInscriptionDuplicateInstance() throws DateExpiredException, DuplicateInscriptionException,
            MaxInscriptionsReachedException, InstanceNotFoundException {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        inscriptionService.inscribeUser(user.getId(), trial.getId(), "1234");

        assertThrows(DuplicateInscriptionException.class, () ->
                inscriptionService.inscribeUser(user.getId(), trial.getId(), "1234"));
    }

    @Test
    public void testNewInscriptionUserNotFound() {

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.inscribeUser(1516326565L, trial.getId(), "1234"));
    }

    @Test
    public void testNewInscriptionTrialNotFound() {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.inscribeUser(user.getId(), 12151531L, "1234"));
    }

    @Test
    public void testNewInscriptionDateExpired() {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createExpiredTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        assertThrows(DateExpiredException.class, () ->
                inscriptionService.inscribeUser(user.getId(), trial.getId(), "1234"));
    }

    @Test
    public void testDorsalAssignment() throws DateExpiredException, DuplicateInscriptionException,
            MaxInscriptionsReachedException, InstanceNotFoundException {
        User user = signUpUser("user");
        User user2 = signUpUser("user2");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        trialDao.save(trial2);

        Inscription inscription = inscriptionService.inscribeUser(user.getId(), trial.getId(), "1234");
        Inscription inscription2 = inscriptionService.inscribeUser(user2.getId(), trial.getId(), "1234");
        Inscription inscription3 = inscriptionService.inscribeUser(user.getId(), trial2.getId(), "12345678");

        Optional<Inscription> i1 = inscriptionDao.findById(inscription.getId());
        Optional<Inscription> i2 = inscriptionDao.findById(inscription2.getId());
        Optional<Inscription> i3 = inscriptionDao.findById(inscription3.getId());

        Inscription foundInscription = null;
        if (i1.isPresent()) {
            foundInscription = i1.get();
        }

        Inscription foundInscription2 = null;
        if (i2.isPresent()) {
            foundInscription2 = i2.get();
        }

        Inscription foundInscription3 = null;
        if (i3.isPresent()) {
            foundInscription3 = i3.get();
        }

        assert foundInscription != null;
        assertEquals(1, foundInscription.getNumber());
        assert foundInscription2 != null;
        assertEquals(2, foundInscription2.getNumber());
        assert foundInscription3 != null;
        assertEquals(1, foundInscription3.getNumber());
    }

    @Test
    public void testFindNoInscriptions() {
        User user = signUpUser("user");
        Block<Inscription> expectedBlock = new Block<>(new ArrayList<>(), false);

        assertEquals(expectedBlock, inscriptionService.findInscriptionsByUser(user.getId(), 0, 1));
    }

    @Test
    public void testFindInscriptionsByUser() {
        User user = signUpUser("user");
        User user2 = signUpUser("user2");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);

        Inscription i1 = createInscription(user, trial);
        Inscription i2 = createInscription(user2, trial);
        Inscription i3 = createInscription(user, trial); i3.setDateTime(LocalDateTime.now().plusDays(1));

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(i1);
        inscriptionDao.save(i2);
        inscriptionDao.save(i3);

        Block<Inscription> expectedBlock = new Block<>(Arrays.asList(i3, i1), false);

        assertEquals(expectedBlock, inscriptionService.findInscriptionsByUser(user.getId(), 0, 2));
    }

    @Test
    public void testFindInscriptionsByPages() {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription i1 = createInscription(user, trial); i1.setDateTime(LocalDateTime.now().plusDays(1));
        Inscription i2 = createInscription(user, trial); i2.setDateTime(LocalDateTime.now().plusDays(2));
        Inscription i3 = createInscription(user, trial); i3.setDateTime(LocalDateTime.now().plusDays(3));

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        inscriptionDao.save(i1);
        inscriptionDao.save(i2);
        inscriptionDao.save(i3);

        Block<Inscription> expectedBlock = new Block<>(Arrays.asList(i3, i2), true);
        assertEquals(expectedBlock, inscriptionService.findInscriptionsByUser(user.getId(), 0, 2));

        expectedBlock = new Block<>(List.of(i1), false);
        assertEquals(expectedBlock, inscriptionService.findInscriptionsByUser(user.getId(), 1, 2));

        expectedBlock = new Block<>(new ArrayList<>(), false);
        assertEquals(expectedBlock, inscriptionService.findInscriptionsByUser(user.getId(), 2, 2));
    }

    // [FUNC-4]
    @Test
    public void testGiveNumberNonExistingInscription(){
        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);

        assertThrows(InstanceNotFoundException.class, () ->
            inscriptionService.giveNumber(1234L, trial.getId(), "1111222233334444"));
    }

    @Test
    public void testGiveNumberIncorrectTrial() {
        User user = signUpUser("user");
        Province province = new Province("province");
        TrialType type = new TrialType("type");

        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, province);

        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        trialDao.save(trial2);
        inscriptionDao.save(inscription);

        assertThrows(IncorrectTrialException.class, () ->
                inscriptionService.giveNumber(inscription.getId(), trial2.getId(), "1111222233334444"));

        assertAll(() -> inscriptionService.giveNumber(inscription.getId(),
                trial.getId(), "1111222233334444"));
    }

    @Test
    public void testGiveNumberDateExpired(){
        User user = signUpUser("user");
        Province province = new Province("province");
        TrialType type = new TrialType("type");

        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        trial.setDateTime(LocalDateTime.now().minusDays(1)); // ayer

        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);

        assertThrows(DateExpiredException.class, () ->
                inscriptionService.giveNumber(inscription.getId(), trial.getId(), inscription.getCardNumber()));
    }

    @Test
    // Comprueba el caso correcto y el caso en el que ya ha sido entregado
    public void testGiveNumberAndAlreadyDelivered()
            throws NumberAlreadyDeliveredException, InvalidCreditCardException,
            InstanceNotFoundException, DateExpiredException, IncorrectTrialException {
        User user = signUpUser("user");
        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);

        assertFalse(inscription.isNumberDelivered());

        // La primera vez debe ser exitosa
        assertEquals(inscription.getNumber(),
                inscriptionService.giveNumber(inscription.getId(), trial.getId(), inscription.getCardNumber()));

        assertTrue(inscription.isNumberDelivered());

        assertThrows(NumberAlreadyDeliveredException.class, () ->
                inscriptionService.giveNumber(inscription.getId(), trial.getId(), inscription.getCardNumber()));
    }

    @Test
    public void testGiveNumberInvalidCreditCard(){
        User user = signUpUser("user");
        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);

        assertThrows(InvalidCreditCardException.class, () ->
                inscriptionService.giveNumber(inscription.getId(), trial.getId(), "0000111122223333"));
    }

    @Test
    public void testRateTrial() throws DateExpiredException, InstanceNotFoundException, AlreadyScoredException, DifferentUserException {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);
        trial.setDateTime(LocalDateTime.now().minusDays(6));

        inscriptionService.rateTrial(inscription.getId(), user.getId(), 1);

        assertTrue(inscription.isScored());

        assertEquals(inscription.getScore(), 1);
    }

    @Test
    public void testRateTrialInscriptionNotFound() throws DateExpiredException, InstanceNotFoundException, AlreadyScoredException, DifferentUserException {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);
        trial.setDateTime(LocalDateTime.now().minusDays(6));

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);


        inscriptionService.rateTrial(inscription.getId(), user.getId(), 1);

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.rateTrial(12525325L, user.getId(), 1));
    }

    @Test
    public void testRateTrialAlreadyScored() throws DateExpiredException, InstanceNotFoundException, AlreadyScoredException, DifferentUserException {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);
        trial.setDateTime(LocalDateTime.now().minusDays(6));

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);


        inscriptionService.rateTrial(inscription.getId(), user.getId(), 1);

        assertThrows(AlreadyScoredException.class, () ->
                inscriptionService.rateTrial(inscription.getId(), user.getId(), 3));
    }

    @Test
    public void testRateTrialDateExpired() {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);


        assertThrows(DateExpiredException.class, () ->
                inscriptionService.rateTrial(inscription.getId(), user.getId(), 1));

        assertThrows(DateExpiredException.class, () ->
                inscriptionService.rateTrial(inscription.getId(), user.getId(), 1));
    }

    @Test
    public void testRateTrialDifferentUserException() {
        User user = signUpUser("user");

        Province province = new Province("province");
        TrialType type = new TrialType("type");
        Trial trial = createTrialWithNameTypeProvince("trial", type, province);
        Inscription inscription = createInscription(user, trial);

        provinceDao.save(province);
        trialTypeDao.save(type);
        trialDao.save(trial);
        inscriptionDao.save(inscription);


        assertThrows(DifferentUserException.class, () ->
            inscriptionService.rateTrial(inscription.getId(), 6491316646L, 1));
    }
}
