package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.TrialType;

import java.util.List;

public interface ContestService {
    List<Province> findAllProvinces();
    List<TrialType> findAllTrialTypes();

}
