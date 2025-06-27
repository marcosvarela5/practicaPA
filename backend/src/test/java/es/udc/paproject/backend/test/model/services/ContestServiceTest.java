package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.ProvinceDao;
import es.udc.paproject.backend.model.entities.TrialType;
import es.udc.paproject.backend.model.entities.TrialTypeDao;
import es.udc.paproject.backend.model.services.ContestService;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ContestServiceTest {

    @Autowired
    private ContestService contestService;

    @Autowired
    private TrialTypeDao trialTypeDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Test
    public void testFindAllProvincesSuccess() {
        List<Province> provinces = contestService.findAllProvinces();
        assertNotNull(provinces);

        List<Province> existingProvinces = StreamSupport.stream(provinceDao.findAll(Sort.unsorted()).spliterator(), false).toList();

        for (Province province : provinces) {
            assertTrue(existingProvinces.contains(province));
        }

        for(int i = 0; i < provinces.size(); i++){
            assertEquals(provinces.get(i).getProvinceName(), existingProvinces.get(i).getProvinceName());
        }
    }

    @Test
    public void testFindAllTrialTypesSuccess() {
        List<TrialType> trialTypes = contestService.findAllTrialTypes();
        assertNotNull(trialTypes);

        List<TrialType> existingTypes = StreamSupport.stream(trialTypeDao.findAll(Sort.unsorted()).spliterator(), false).toList();


        for (TrialType trialType : trialTypes) {
            assertTrue(existingTypes.contains(trialType));
        }

        for(int i = 0; i < trialTypes.size(); i++){
            assertEquals(trialTypes.get(i).getTrialTypeName(), existingTypes.get(i).getTrialTypeName());
        }
    }
}

