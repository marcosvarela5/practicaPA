package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.ProvinceDao;
import es.udc.paproject.backend.model.entities.TrialType;

import es.udc.paproject.backend.model.entities.TrialTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly=true)
public class ContestServiceImpl implements ContestService{

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private TrialTypeDao trialTypeDao;

    @Override
    public List<Province> findAllProvinces() {
        return StreamSupport.stream(provinceDao.findAll(Sort.unsorted()).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrialType> findAllTrialTypes() {
        return StreamSupport.stream(trialTypeDao.findAll(Sort.unsorted()).spliterator(), false)
                .collect(Collectors.toList());
    }
}
