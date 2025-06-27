package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.model.services.TrialService;
import es.udc.paproject.backend.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TrialServiceTest {

    @Autowired
    private TrialService trialService;

    @Autowired
    private TrialTypeDao trialTypeDao;

    @Autowired
    private TrialDao trialDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private InscriptionDao inscriptionDao;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private UserService userService;

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

    private Inscription createInscription(User user, Trial trial){
        return new Inscription(user, trial, 7, "1111222233334444");
    }

    private Trial createTrialWithNameTypeProvince(String name, TrialType type, Province province){
        return new Trial(name, "trialDescription",
                LocalDateTime.of(2024, 3, 5, 15, 39, 46),
                BigDecimal.valueOf(10), 100, "place", type, province, 0, 0);
    }

    @Test
    public void testFindTrialsByTrialType() {

        TrialType typeGood = new TrialType("typeGood");
        TrialType typeBad = new TrialType("typeBad");

        Province province = new Province("province");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", typeGood, province);
        Trial trial2 = createTrialWithNameTypeProvince("trial2", typeBad, province);
        Trial trial3 = createTrialWithNameTypeProvince("trial3", typeGood, province);

        trialTypeDao.save(typeGood);
        trialTypeDao.save(typeBad);

        provinceDao.save(province);

        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);

        Block<Trial> expectedBlock = new Block<>(Arrays.asList(trial1, trial3), false);

        assertEquals(expectedBlock, trialService.findTrials
                (typeGood.getId(), null, null, null, 0, 2));
    }

    @Test
    public void testFindTrialsByProvince() {

        TrialType type = new TrialType("type");
        Province provinceGood = new Province("provinceGood");
        Province provinceBad = new Province("provinceBad");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", type, provinceBad);
        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, provinceGood);
        Trial trial3 = createTrialWithNameTypeProvince("trial3", type, provinceGood);

        trialTypeDao.save(type);

        provinceDao.save(provinceGood);
        provinceDao.save(provinceBad);

        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);

        Block<Trial> expectedBlock = new Block<>(Arrays.asList(trial2, trial3), false);

        assertEquals(expectedBlock, trialService.findTrials
                (null, provinceGood.getId(), null, null, 0, 2));
    }

    @Test
    public void testFindTrialsByStartDate() {

        TrialType type = new TrialType("type");
        Province province = new Province("province");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", type, province);
        trial1.setDateTime(LocalDateTime.of(2025, 2, 14, 5, 15, 2));

        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, province);
        trial2.setDateTime(LocalDateTime.of(2024, 7, 17, 0, 55, 5));

        Trial trial3 = createTrialWithNameTypeProvince("trial3", type, province);

        trialTypeDao.save(type);

        provinceDao.save(province);

        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);

        Block<Trial> expectedBlock = new Block<>(Arrays.asList(trial1, trial2), false);

        assertEquals(expectedBlock, trialService.findTrials
                (null, null, LocalDate.of(2024, 3, 6), null, 0, 2));
    }

    @Test
    public void testFindTrialsByEndDate() {

        TrialType type = new TrialType("type");
        Province province = new Province("province");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", type, province);
        trial1.setDateTime(LocalDateTime.of(2023, 2, 14, 5, 15, 2));

        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, province);
        trial2.setDateTime(LocalDateTime.of(2024, 7, 17, 0, 55, 5));

        Trial trial3 = createTrialWithNameTypeProvince("trial3", type, province);

        trialTypeDao.save(type);

        provinceDao.save(province);

        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);

        Block<Trial> expectedBlock = new Block<>(Arrays.asList(trial3, trial1), false);

        assertEquals(expectedBlock, trialService.findTrials
                (null, null, null, LocalDate.of(2024, 3, 6), 0, 2));
    }

    @Test
    public void testFindTrialsByAllCriteria() {

        TrialType typeGood = new TrialType("typeGood");
        TrialType typeBad = new TrialType("typeBad");

        Province provinceGood = new Province("provinceGood");
        Province provinceBad = new Province("provinceBad");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", typeBad, provinceGood);
        trial1.setDateTime(LocalDateTime.of(2023, 2, 14, 5, 15, 2));

        Trial trial2 = createTrialWithNameTypeProvince("trial2", typeGood, provinceGood);
        trial2.setDateTime(LocalDateTime.of(2024, 7, 17, 0, 55, 5));

        Trial trial3 = createTrialWithNameTypeProvince("trial3", typeGood, provinceGood);
        trial3.setDateTime(LocalDateTime.of(2024, 8, 17, 0, 55, 5));

        Trial trial4 = createTrialWithNameTypeProvince("trial4", typeGood, provinceBad);

        trialTypeDao.save(typeGood);
        trialTypeDao.save(typeBad);

        provinceDao.save(provinceGood);
        provinceDao.save(provinceBad);

        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);
        trialDao.save(trial4);

        Block<Trial> expectedBlock = new Block<>(List.of(trial2), false);

        assertEquals(expectedBlock, trialService.findTrials
                (typeGood.getId(), provinceGood.getId(), LocalDate.of(2024, 6, 30),
                        LocalDate.of(2024, 8, 17), 0, 1));
    }

    @Test
    public void testFindAllTrials() { // This also tests that the "orderBy" is correct

        TrialType typeGood = new TrialType("typeGood");
        TrialType typeBad = new TrialType("typeBad");

        Province provinceGood = new Province("provinceGood");
        Province provinceBad = new Province("provinceBad");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", typeBad, provinceGood);
        trial1.setDateTime(LocalDateTime.of(2023, 2, 14, 5, 15, 2));

        Trial trial2 = createTrialWithNameTypeProvince("trial2", typeGood, provinceGood);
        trial2.setDateTime(LocalDateTime.of(2024, 7, 17, 0, 55, 5));

        Trial trial3 = createTrialWithNameTypeProvince("trial3", typeGood, provinceGood);
        trial3.setDateTime(LocalDateTime.of(2024, 8, 17, 0, 55, 5));

        Trial trial4 = createTrialWithNameTypeProvince("trial4", typeGood, provinceBad);

        trialTypeDao.save(typeGood);
        trialTypeDao.save(typeBad);

        provinceDao.save(provinceGood);
        provinceDao.save(provinceBad);

        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);
        trialDao.save(trial4);

        Block<Trial> expectedBlock = new Block<>(List.of(trial3, trial2, trial4, trial1), false);

        assertEquals(expectedBlock, trialService.findTrials
                (null, null, null, null, 0, 4));
    }

    @Test
    public void testFindNoTrials() {

        TrialType type = new TrialType("type");
        TrialType type2 = new TrialType("type2");

        Province province = new Province("province");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", type, province);

        trialTypeDao.save(type);
        trialTypeDao.save(type2);
        provinceDao.save(province);
        trialDao.save(trial1);

        Block<Trial> expectedBlock = new Block<>(new ArrayList<>(), false);

        assertEquals(expectedBlock, trialService.findTrials
                (type2.getId(), null, null, null, 0, 1));
    }

    @Test
    public void testFindTrialsByPages() {

        TrialType type = new TrialType("type");

        Province province = new Province("province");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", type, province);
        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, province);
        Trial trial3 = createTrialWithNameTypeProvince("trial3", type, province);

        trialTypeDao.save(type);
        provinceDao.save(province);
        trialDao.save(trial1);
        trialDao.save(trial2);
        trialDao.save(trial3);

        Block<Trial> expectedBlock = new Block<>(Arrays.asList(trial1, trial2), true);
        assertEquals(expectedBlock, trialService.findTrials
                (null, null, null, null, 0, 2));

        expectedBlock = new Block<>(Arrays.asList(trial3), false);
        assertEquals(expectedBlock, trialService.findTrials
                (null, null, null, null, 1, 2));

        expectedBlock = new Block<>(new ArrayList<>(), false);
        assertEquals(expectedBlock, trialService.findTrials
                (null, null, null, null, 2, 2));
    }

    @Test
    public void testFindTrialDetails() {

        User user = signUpUser("user");
        User user2 = signUpUser("user2");

        TrialType type = new TrialType("type");

        Province province = new Province("province");

        Trial trial1 = createTrialWithNameTypeProvince("trial1", type, province);
        Trial trial2 = createTrialWithNameTypeProvince("trial2", type, province);

        trialTypeDao.save(type);
        provinceDao.save(province);
        trialDao.save(trial1);
        trialDao.save(trial2);

        Inscription i1 = createInscription(user, trial1);
        Inscription i2 = createInscription(user2, trial1);
        Inscription i3 = createInscription(user, trial2);

        inscriptionDao.save(i1);
        inscriptionDao.save(i2);
        inscriptionDao.save(i3);

        Trial foundTrial = null;
        try {
            foundTrial = trialService.findTrialDetailsById(trial1.getId());
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        }

        Trial foundTrial2 = null;
        try {
            foundTrial2 = trialService.findTrialDetailsById(trial2.getId());
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(trial1, foundTrial);

        assertEquals(trial2, foundTrial2);

    }


}
